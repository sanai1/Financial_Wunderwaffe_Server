package org.example.financial_wunderwaffe_server.model.repository

import org.example.financial_wunderwaffe_server.model.entity.CalculationShareOfAssetEntity
import org.example.financial_wunderwaffe_server.model.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CalculationShareOfAssetRepository: JpaRepository<CalculationShareOfAssetEntity, Long> {
    fun getAllByUser(user: UserEntity): List<CalculationShareOfAssetEntity>
}