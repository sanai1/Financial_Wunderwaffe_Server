package org.example.financial_wunderwaffe_server.model.request

import org.example.financial_wunderwaffe_server.database.entity.TypeAssetEntity

data class TypeAssetView(
    val id: Long,
    val name: String,
    val color: List<Int>
) {
    fun toTypeAssetEntity(): TypeAssetEntity =
        TypeAssetEntity(id, name, color)
}