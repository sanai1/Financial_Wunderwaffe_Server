package org.example.financial_wunderwaffe_server.model.request

import org.example.financial_wunderwaffe_server.database.entity.CalculationShareOfAssetEntity
import org.example.financial_wunderwaffe_server.database.entity.UserEntity
import java.time.LocalDate
import java.util.UUID

data class CalculationShareOfAssetView(
    val id: Long,
    val userUID: UUID,
    val date: LocalDate,
    val listShareOfAsset: List<ShareOfAssetView>
) {
    fun toCalculationShareOfAssetEntity(user: UserEntity): CalculationShareOfAssetEntity =
        CalculationShareOfAssetEntity(
            user = user,
            date = date
        )
    fun toCalculationShareOfAssetEntity(id: Long, user: UserEntity): CalculationShareOfAssetEntity =
        CalculationShareOfAssetEntity(
            id = id,
            user = user,
            date = date
        )
}