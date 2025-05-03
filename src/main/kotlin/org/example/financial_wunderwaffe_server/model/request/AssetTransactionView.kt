package org.example.financial_wunderwaffe_server.model.request

import org.example.financial_wunderwaffe_server.database.entity.AssetEntity
import org.example.financial_wunderwaffe_server.database.entity.AssetTransactionEntity

data class AssetTransactionView(
    val id: Long,
    val assetID: Long,
    val isSale: Boolean,
    val amount: Long,
    val date: String,
) {
    fun toAssetTransactionEntity(assetEntity: AssetEntity): AssetTransactionEntity =
        AssetTransactionEntity(
            id = id,
            asset = assetEntity,
            isSale = isSale,
            amount = amount,
            date = date
        )
}
