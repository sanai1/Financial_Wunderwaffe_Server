package org.example.financial_wunderwaffe_server.service.implementation

import org.example.financial_wunderwaffe_server.model.request.AssetInformationView
import org.example.financial_wunderwaffe_server.model.request.BudgetAnalyticsView
import org.example.financial_wunderwaffe_server.model.request.CapitalAnalyticsView
import org.example.financial_wunderwaffe_server.model.request.TransactionView
import org.example.financial_wunderwaffe_server.service.*
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class AnalyticsServiceImplementation(
    private val transactionService: TransactionService,
    private val categoriesService: CategoryService,
    private val assetService: AssetService,
    private val assetInformationService: AssetInformationService,
) : AnalyticsService {
    override fun getBudgetByMonth(userUID: UUID): List<BudgetAnalyticsView> {
        val transactions = getGroupTransactions(userUID)
        val listBudgetAnalyticsView = mutableListOf<BudgetAnalyticsView>()
        val categories = categoriesService.findByUserUID(userUID)
        transactions.forEach { entryByMonth ->
            listBudgetAnalyticsView.add(
                BudgetAnalyticsView(
                    month = entryByMonth.key,
                    listCategory = entryByMonth.value.map { category ->
                        val categoryView = categories.find { it.id == category.key }!!
                        var amountLarge = 0L
                        var amountUsual = 0L
                        category.value.forEach { transaction ->
                            if (transaction.type) amountLarge += transaction.amount
                            else amountUsual += transaction.amount
                        }
                        BudgetAnalyticsView.CategoryAnalyticsView(
                            id = category.key,
                            title = categoryView.name,
                            isIncome = categoryView.type,
                            amountLarge = amountLarge,
                            amountUsual = amountUsual
                        )
                    }
                )
            )
        }
        return listBudgetAnalyticsView
    }

    override fun getCapitalByMonth(userUID: UUID): List<CapitalAnalyticsView> {
        val assets = assetService.findByUserUID(userUID)
        val categories = categoriesService.findByUserUID(userUID)
        val listAssetInformation = mutableListOf<AssetInformationView>()
        assets.forEach { asset ->
            assetInformationService.findAssetInformationByAsset(asset.id).forEach { assetInformationView ->
                listAssetInformation.add(assetInformationView)
            }
        }
        listAssetInformation.sortBy { YearMonth.parse(it.date, DateTimeFormatter.ofPattern("dd.MM.yyyy")) }

        val mapTransactions = mutableMapOf<String, MutableMap<Long, List<TransactionView>>>()
        getGroupTransactions(userUID).forEach { entry ->
            val mapByCategory = mutableMapOf<Long, List<TransactionView>>()
            entry.value.forEach { transactionByCategory ->
                val listTransactions = mutableListOf<TransactionView>()
                transactionByCategory.value.forEach { transaction ->
                    listTransactions.add(transaction)
                }
                mapByCategory[transactionByCategory.key] = listTransactions
            }
            if (mapByCategory.isNotEmpty()) mapTransactions[entry.key] = mapByCategory
        }
        val resultList = mutableListOf<CapitalAnalyticsView>()
        var fiat = 0L
        var startMonth = if (mapTransactions.isEmpty()) {
            YearMonth.parse(listAssetInformation.first().date, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        } else {
            minOf(
                YearMonth.parse(listAssetInformation.first().date, DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                mapTransactions.keys.minOf { YearMonth.parse(it, DateTimeFormatter.ofPattern("MM.yyyy")) })
        }
        val finishMonth = if (mapTransactions.isEmpty()) {
            YearMonth.parse(listAssetInformation.last().date, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        } else {
            maxOf(
                YearMonth.parse(listAssetInformation.last().date, DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                mapTransactions.keys.maxOf { YearMonth.parse(it, DateTimeFormatter.ofPattern("MM.yyy")) })
        }
        while (startMonth.isBefore(finishMonth.plusMonths(1))) {
            val mapAsset = mutableMapOf<Long, CapitalAnalyticsView.AssetAnalyticsView>()
            var incomeUsual = 0L
            var expenseUsual = 0L
            var incomeLarge = 0L
            var expenseLarge = 0L
            val nowMonth = "${
                startMonth.monthValue.let {
                    if (it < 10) "0$it" else it
                }
            }.${startMonth.year}"
            if (nowMonth in mapTransactions.keys) {
                mapTransactions[nowMonth]!!.forEach { entry ->
                    when (categories.find { it.id == entry.key }?.type) {
                        true -> {
                            entry.value.forEach { transaction ->
                                if (transaction.type) incomeLarge += transaction.amount
                                else incomeUsual += transaction.amount
                            }
                        }

                        false -> {
                            entry.value.forEach { transaction ->
                                if (transaction.type) expenseLarge += transaction.amount
                                else expenseUsual += transaction.amount
                            }
                        }

                        null -> {}
                    }
                }
            }
            fiat += incomeUsual + incomeLarge - expenseUsual - expenseLarge
            val assetFiat = assets.find { it.title == "Фиат" }!!
            mapAsset[assetFiat.id] = CapitalAnalyticsView.AssetAnalyticsView(
                id = assetFiat.id,
                title = assetFiat.title,
                amount = fiat
            )
            listAssetInformation.groupBy { it.assetId }.forEach { entry ->
                if (entry.key != assetFiat.id) {
                    val price = entry.value.filter { it.typeInformation == "price" }.firstOrNull {
                        if (YearMonth.parse(
                                it.date,
                                DateTimeFormatter.ofPattern("dd.MM.yyyy")
                            ) == startMonth
                        ) true
                        else if (YearMonth.parse(it.date, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                                .isBefore(startMonth)
                        ) true
                        else false
                    }
                    val amount: Long
                    if (price != null) {
                        val transaction = entry.value.filter { it.typeInformation == "transaction" }.filter {
                            val dateTransaction = YearMonth.parse(
                                it.date,
                                DateTimeFormatter.ofPattern("dd.MM.yyyy")
                            )
                            (dateTransaction == startMonth || dateTransaction.isBefore(startMonth)) && LocalDate.parse(
                                it.date,
                                DateTimeFormatter.ofPattern("dd.MM.yyyy")
                            ).isAfter(LocalDate.parse(price.date, DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                        }
                        amount = transaction.sumOf {
                            if (it.isSale!!) (-1) * it.amount!!
                            else it.amount!!
                        } + price.currentPrice!!
                    } else {
                        val transaction = entry.value.filter { it.typeInformation == "transaction" }.filter {
                            val dateTransaction = YearMonth.parse(
                                it.date,
                                DateTimeFormatter.ofPattern("dd.MM.yyyy")
                            )
                            dateTransaction == startMonth || dateTransaction.isBefore(startMonth)
                        }
                        amount = if (transaction.isEmpty()) {
                            0
                        } else {
                            transaction.sumOf {
                                if (it.isSale!!) (-1) * it.amount!!
                                else it.amount!!
                            }
                        }
                    }
                    mapAsset[entry.key] = CapitalAnalyticsView.AssetAnalyticsView(
                        id = entry.key,
                        title = assets.find { it.id == entry.key }?.title ?: "Not title",
                        amount = amount
                    )
                }
            }
            resultList.add(
                CapitalAnalyticsView(
                    month = nowMonth,
                    saveRate = if (incomeUsual == 0L && expenseUsual == 0L) {
                        0.0
                    } else if (incomeUsual == 0L) {
                        -100.0
                    } else {
                        100.0 * (incomeUsual - expenseUsual).toDouble() / incomeUsual.toDouble()
                    },
                    listAsset = mapAsset.map { entry ->
                        entry.value
                    }
                ))
            startMonth = startMonth.plusMonths(1)
        }
        return resultList
    }

    private fun getGroupTransactions(userUID: UUID) = transactionService.findByUserUID(userUID).groupBy { transaction ->
        SimpleDateFormat("MM.yyyy", Locale.getDefault()).format(
            SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(transaction.date)
        )
    }.mapValues { entry ->
        entry.value.groupBy { transaction ->
            transaction.categoryID
        }
    }
}