package org.example.financial_wunderwaffe_server.model.request

import org.example.financial_wunderwaffe_server.database.entity.AssetEntity
import org.example.financial_wunderwaffe_server.database.entity.UserEntity
import java.util.UUID

data class AssetView(
    val id: Long,
    val userUID: UUID,
    val title: String,
    val amount: Long,
) {
    fun toAssetEntity(user: UserEntity): AssetEntity =
        AssetEntity(
            id = id,
            user = user,
            title = title,
        )
}
