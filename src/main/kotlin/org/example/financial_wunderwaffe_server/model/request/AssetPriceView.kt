package org.example.financial_wunderwaffe_server.model.request

import org.example.financial_wunderwaffe_server.database.entity.AssetEntity
import org.example.financial_wunderwaffe_server.database.entity.AssetPriceEntity

data class AssetPriceView(
    val id: Long,
    val assetID: Long,
    val date: String,
    val oldPrice: Long,
    val currentPrice: Long,
) {
    fun toAssetPriceEntity(asset: AssetEntity): AssetPriceEntity =
        AssetPriceEntity(
            id = id,
            asset = asset,
            date = date,
            oldPrice = oldPrice,
            currentPrice = currentPrice,
        )
}
