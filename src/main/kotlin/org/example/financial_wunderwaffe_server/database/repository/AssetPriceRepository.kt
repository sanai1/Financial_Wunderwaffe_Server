package org.example.financial_wunderwaffe_server.database.repository

import org.example.financial_wunderwaffe_server.database.entity.AssetEntity
import org.example.financial_wunderwaffe_server.database.entity.AssetPriceEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AssetPriceRepository : JpaRepository<AssetPriceEntity, Long> {
    fun findByAsset(assetEntity: AssetEntity): List<AssetPriceEntity>
}