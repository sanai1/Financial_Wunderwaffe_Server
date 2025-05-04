package org.example.financial_wunderwaffe_server.service.implementation

import org.example.financial_wunderwaffe_server.model.request.AssetInformationView
import org.example.financial_wunderwaffe_server.model.request.BudgetAnalyticsView
import org.example.financial_wunderwaffe_server.model.request.CapitalAnalyticsView
import org.example.financial_wunderwaffe_server.service.*
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.time.LocalDate
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
            entryByMonth.value.forEach { entryByCategory ->
                val category = categories.find { it.id == entryByCategory.key } ?: return@forEach
                listBudgetAnalyticsView.add(
                    BudgetAnalyticsView(
                        month = entryByMonth.key,
                        listCategory = entryByCategory.value.map {
                            BudgetAnalyticsView.CategoryAnalyticsView(
                                id = entryByCategory.key,
                                title = category.name,
                                isIncome = category.type,
                                isLarge = it.type,
                                amount = it.amount
                            )
                        }
                    ))
            }
        }
        return listBudgetAnalyticsView
    }

    override fun getCapitalByMonth(userUID: UUID): List<CapitalAnalyticsView> {
        val assets = assetService.findByUserUID(userUID)
        val listAssetInformation = mutableListOf<AssetInformationView>()
        assets.forEach { asset ->
            assetInformationService.findAssetInformationByAsset(asset.id).forEach { assetInformationView ->
                listAssetInformation.add(assetInformationView)
            }
        }
        val groupListAssetInformation = listAssetInformation.groupBy { assetInformation ->
            SimpleDateFormat("MM.yyyy", Locale.getDefault()).format(
                SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(assetInformation.date)
            )
        }.mapValues { entry ->
            entry.value.groupBy { assetInformation ->
                assetInformation.assetId
            }
        }
        val transactionsBudget = getGroupTransactions(userUID).mapValues { group ->
            group.value.mapValues { entry ->
                entry.value.filter { it.type.not() }
            }
        }
        val listCapitalAnalyticsView = mutableListOf<CapitalAnalyticsView>()
        val categories = categoriesService.findByUserUID(userUID)
            .filter { (it.name in listOf("Продажа актива", "Покупка актива")).not() }
        groupListAssetInformation.forEach { entryByMonth ->
            var expense = 0.0
            var income = 0.0
            var flagIncome = false
            transactionsBudget[entryByMonth.key]?.forEach { entryTransaction ->
                val category = categories.find { it.id == entryTransaction.key } ?: return@forEach
                when (category.type) {
                    true -> {
                        income += entryTransaction.value.sumOf { it.amount }
                        flagIncome = true
                    }

                    false -> expense += entryTransaction.value.sumOf { it.amount }
                }
            }
            if (flagIncome.not()) income = 1.0
            listCapitalAnalyticsView.add(
                CapitalAnalyticsView(
                    month = entryByMonth.key,
                    saveRate = (10_000.0 * (income - expense) / income).toLong().toDouble() / 100.0,
                    listAsset = entryByMonth.value.map { entryByAsset ->
                        val price = entryByAsset.value.filter { it.typeInformation == "price" }
                            .maxWith(compareBy<AssetInformationView> {
                                LocalDate.parse(it.date, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                            }.thenBy { it.currentPrice ?: 0 })
                        val transaction = entryByAsset.value.filter { it.typeInformation == "transaction" }.filter {
                            SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(it.date).after(
                                SimpleDateFormat("dd.MM.yyyy").parse(price.date)
                            )
                        }.groupBy { it.isSale }
                        CapitalAnalyticsView.AssetAnalyticsView(
                            id = entryByAsset.key,
                            title = assets.find { it.id == entryByAsset.key }?.title ?: "No title",
                            amount = (price.currentPrice ?: 0) + (transaction[false]?.sumOf { it.amount ?: 0 }
                                ?: 0) - (transaction[true]?.sumOf { it.amount ?: 0 } ?: 0),
                        )
                    }
                ))
        }
        return listCapitalAnalyticsView
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