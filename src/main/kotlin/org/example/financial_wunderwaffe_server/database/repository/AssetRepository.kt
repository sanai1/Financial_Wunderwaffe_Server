package org.example.financial_wunderwaffe_server.database.repository

import org.example.financial_wunderwaffe_server.database.entity.AssetEntity
import org.example.financial_wunderwaffe_server.database.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AssetRepository : JpaRepository<AssetEntity, Long> {
    fun findAssetsByUser(user: UserEntity): List<AssetEntity>
}