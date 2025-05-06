package org.example.financial_wunderwaffe_server.service.implementation

import org.example.financial_wunderwaffe_server.database.repository.AssetPriceRepository
import org.example.financial_wunderwaffe_server.database.repository.AssetRepository
import org.example.financial_wunderwaffe_server.database.repository.AssetTransactionRepository
import org.example.financial_wunderwaffe_server.model.request.AssetInformationView
import org.example.financial_wunderwaffe_server.model.request.AssetPriceView
import org.example.financial_wunderwaffe_server.model.request.AssetTransactionView
import org.example.financial_wunderwaffe_server.model.request.TransactionView
import org.example.financial_wunderwaffe_server.service.AssetInformationService
import org.example.financial_wunderwaffe_server.service.CategoryService
import org.example.financial_wunderwaffe_server.service.TransactionService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class AssetInformationServiceImplementation(
    private val assetRepository: AssetRepository,
    private val assetPriceRepository: AssetPriceRepository,
    private val assetTransactionRepository: AssetTransactionRepository,
    private val transactionService: TransactionService,
    private val categoryService: CategoryService,
) : AssetInformationService {
    override fun createAssetPrice(assetPriceView: AssetPriceView): Long {
        val asset = assetRepository.findByIdOrNull(assetPriceView.assetID)
        return if (asset != null) assetPriceRepository.save(assetPriceView.toAssetPriceEntity(asset)).id
        else 0L
    }

    override fun createAssetTransaction(assetTransactionView: AssetTransactionView): Long {
        val asset = assetRepository.findByIdOrNull(assetTransactionView.assetID)
        return if (asset != null) {
            assetTransactionRepository.save(assetTransactionView.toAssetTransactionEntity(asset)).id
            transactionService.create(
                TransactionView(
                    id = assetTransactionView.id,
                    userUID = asset.user.uid,
                    categoryID = categoryService.findByUserUID(asset.user.uid).first {
                        when (assetTransactionView.isSale) {
                            true -> it.name == "Продажа актива"
                            false -> it.name == "Покупка актива"
                        }
                    }.id,
                    amount = assetTransactionView.amount,
                    date = assetTransactionView.date,
                    type = false,
                    description = when (assetTransactionView.isSale) {
                        true -> "Продажа актива: ${asset.title}"
                        false -> "Покупка актива: ${asset.title}"
                    }
                )
            )
        } else 0L
    }

    override fun findAssetInformationByAsset(assetId: Long): List<AssetInformationView> {
        val asset = assetRepository.findByIdOrNull(assetId) ?: return emptyList()
        val listAssetPrice = assetPriceRepository.findByAsset(asset)
        val listAssetTransaction = assetTransactionRepository.findAssetTransactionByAsset(asset)
        val listAssetInformationView = mutableListOf<AssetInformationView>()
        listAssetPrice.forEach {
            listAssetInformationView.add(
                AssetInformationView(
                    assetId = it.asset.id,
                    typeInformation = "price",
                    date = it.date,
                    amount = null,
                    isSale = null,
                    oldPrice = it.oldPrice,
                    currentPrice = it.currentPrice
                )
            )
        }
        listAssetTransaction.forEach {
            if (it.date != "01.01.1900") {
                listAssetInformationView.add(
                    AssetInformationView(
                        assetId = it.asset.id,
                        typeInformation = "transaction",
                        date = it.date,
                        amount = it.amount,
                        isSale = it.isSale,
                        oldPrice = null,
                        currentPrice = null
                    )
                )
            }
        }
        return listAssetInformationView.map {
            it to SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(it.date)
        }.sortedByDescending { (_, parsedDate) -> parsedDate }.map { (information, _) -> information }
    }
}