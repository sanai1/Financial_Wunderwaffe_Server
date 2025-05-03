package org.example.financial_wunderwaffe_server.service.implementation

import org.example.financial_wunderwaffe_server.database.entity.AssetPriceEntity
import org.example.financial_wunderwaffe_server.database.repository.AssetPriceRepository
import org.example.financial_wunderwaffe_server.database.repository.AssetRepository
import org.example.financial_wunderwaffe_server.database.repository.UserRepository
import org.example.financial_wunderwaffe_server.model.request.AssetView
import org.example.financial_wunderwaffe_server.service.AssetService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class AssetServiceImplementation(
    private val assetRepository: AssetRepository,
    private val assetPriceRepository: AssetPriceRepository,
    private val userRepository: UserRepository,
) : AssetService {
    override fun findByUserUID(userUID: UUID): List<AssetView> =
        assetRepository.findAssetsByUser(
            userRepository.findById(userUID).get()
        ).map { assetEntity ->
            assetEntity.toAssetView(
                amount = assetPriceRepository.findByAsset(assetEntity).maxWith(compareBy<AssetPriceEntity> {
                    LocalDate.parse(it.date, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                }.thenBy { it.id }).currentPrice
            )
        }

    override fun create(assetView: AssetView): Long {
        val user = userRepository.findByIdOrNull(assetView.userUID)
        return if (user != null) {
            val asset = assetRepository.save(assetView.toAssetEntity(user))
            assetPriceRepository.save(
                AssetPriceEntity(
                    asset = asset,
                    date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    oldPrice = 0,
                    currentPrice = 0
                )
            )
            asset.id
        } else 0L
    }
}