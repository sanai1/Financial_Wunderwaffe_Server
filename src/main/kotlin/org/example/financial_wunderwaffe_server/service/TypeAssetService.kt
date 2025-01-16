package org.example.financial_wunderwaffe_server.service

import org.example.financial_wunderwaffe_server.model.request.TypeAssetView

interface TypeAssetService {
    fun findAll(): List<TypeAssetView>
    fun create(typeAssetView: TypeAssetView): Long
    fun update(typeAssetView: TypeAssetView): Boolean
}