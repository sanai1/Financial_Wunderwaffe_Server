package org.example.financial_wunderwaffe_server.database.repository

import org.example.financial_wunderwaffe_server.database.entity.CalculationShareOfAssetEntity
import org.example.financial_wunderwaffe_server.database.entity.ShareOfAssetEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ShareOfAssetRepository: JpaRepository<ShareOfAssetEntity, Long> {
    fun getAllByCalculationShareOfAsset(
        calculationShareOfAsset: CalculationShareOfAssetEntity
    ): List<ShareOfAssetEntity>
}