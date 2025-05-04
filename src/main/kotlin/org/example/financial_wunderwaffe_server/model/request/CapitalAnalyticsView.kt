package org.example.financial_wunderwaffe_server.model.request

data class CapitalAnalyticsView(
    val month: String,
    val saveRate: Double,
    val listAsset: List<AssetAnalyticsView>,
) {
    data class AssetAnalyticsView(
        val id: Long,
        val title: String,
        val amount: Long,
    )
}
