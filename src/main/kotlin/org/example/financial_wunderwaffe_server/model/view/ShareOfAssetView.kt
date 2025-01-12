package org.example.financial_wunderwaffe_server.model.view

import org.example.financial_wunderwaffe_server.model.entity.CalculationShareOfAssetEntity
import org.example.financial_wunderwaffe_server.model.entity.ShareOfAssetEntity

data class ShareOfAssetView(
    val id: Long,
    val typeAsset: TypeAssetView,
    val share: Long
) {
    fun toShareOfAssetEntity(calculationShareOfAssetEntity: CalculationShareOfAssetEntity): ShareOfAssetEntity =
        ShareOfAssetEntity(
            typeAsset = typeAsset.toTypeAssetEntity(),
            calculationShareOfAsset = calculationShareOfAssetEntity,
            share = share
        )
    fun toShareOfAssetEntity(id: Long, calculationShareOfAssetEntity: CalculationShareOfAssetEntity): ShareOfAssetEntity =
        ShareOfAssetEntity(
            id = id,
            typeAsset = typeAsset.toTypeAssetEntity(),
            calculationShareOfAsset = calculationShareOfAssetEntity,
            share = share
        )
}