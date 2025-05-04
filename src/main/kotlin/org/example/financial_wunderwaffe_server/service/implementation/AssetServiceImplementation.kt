package org.example.financial_wunderwaffe_server.service.implementation

import org.example.financial_wunderwaffe_server.database.entity.AssetPriceEntity
import org.example.financial_wunderwaffe_server.database.repository.AssetPriceRepository
import org.example.financial_wunderwaffe_server.database.repository.AssetRepository
import org.example.financial_wunderwaffe_server.database.repository.AssetTransactionRepository
import org.example.financial_wunderwaffe_server.database.repository.UserRepository
import org.example.financial_wunderwaffe_server.model.request.AssetView
import org.example.financial_wunderwaffe_server.service.AssetService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class AssetServiceImplementation(
    private val assetRepository: AssetRepository,
    private val assetPriceRepository: AssetPriceRepository,
    private val assetTransactionRepository: AssetTransactionRepository,
    private val userRepository: UserRepository,
) : AssetService {
    override fun findByUserUID(userUID: UUID): List<AssetView> =
        assetRepository.findAssetsByUser(
            userRepository.findById(userUID).get()
        ).map { assetEntity ->
            val afterPrice = assetPriceRepository.findByAsset(assetEntity).maxWith(compareBy<AssetPriceEntity> {
                LocalDate.parse(it.date, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            }.thenBy { it.id })
            val afterTransactions = assetTransactionRepository.findAssetTransactionByAsset(assetEntity).filter {
                SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(it.date).after(
                    SimpleDateFormat("dd.MM.yyyy").parse(afterPrice.date)
                )
            }.groupBy { it.isSale }
            assetEntity.toAssetView(
                amount = afterPrice.currentPrice + (afterTransactions[false]?.sumOf { it.amount }
                    ?: 0) - (afterTransactions[true]?.sumOf { it.amount } ?: 0),
            )
        }

    override fun create(assetView: AssetView): Long {
        val user = userRepository.findByIdOrNull(assetView.userUID)
        return if (user != null) {
            val asset = assetRepository.save(assetView.toAssetEntity(user))
            assetPriceRepository.save(
                AssetPriceEntity(
                    asset = asset,
                    date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    oldPrice = 0,
                    currentPrice = 0
                )
            )
            asset.id
        } else 0L
    }
}