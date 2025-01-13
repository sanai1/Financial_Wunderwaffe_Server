package org.example.financial_wunderwaffe_server.database.repository

import org.example.financial_wunderwaffe_server.database.entity.CalculationShareOfAssetEntity
import org.example.financial_wunderwaffe_server.database.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CalculationShareOfAssetRepository: JpaRepository<CalculationShareOfAssetEntity, Long> {
    fun getAllByUser(user: UserEntity): List<CalculationShareOfAssetEntity>
}