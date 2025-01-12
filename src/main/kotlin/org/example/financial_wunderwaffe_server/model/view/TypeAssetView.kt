package org.example.financial_wunderwaffe_server.model.view

import org.example.financial_wunderwaffe_server.model.entity.TypeAssetEntity

data class TypeAssetView(
    val id: Long,
    val name: String,
    val description: String
) {
    fun toTypeAssetEntity(): TypeAssetEntity =
        TypeAssetEntity(id, name, description)
}