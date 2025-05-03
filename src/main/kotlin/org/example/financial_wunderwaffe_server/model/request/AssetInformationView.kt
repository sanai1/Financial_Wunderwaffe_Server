package org.example.financial_wunderwaffe_server.model.request

data class AssetInformationView(
    val assetId: Long,
    val typeInformation: String,
    val date: String,
    val amount: Long?,
    val isSale: Boolean?,
    val oldPrice: Long?,
    val currentPrice: Long?,
)
