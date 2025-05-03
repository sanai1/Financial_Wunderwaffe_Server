package org.example.financial_wunderwaffe_server.database.repository

import org.example.financial_wunderwaffe_server.database.entity.AssetEntity
import org.example.financial_wunderwaffe_server.database.entity.AssetTransactionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AssetTransactionRepository : JpaRepository<AssetTransactionEntity, Long> {
    fun findAssetTransactionByAsset(asset: AssetEntity): List<AssetTransactionEntity>
}