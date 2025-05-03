package org.example.financial_wunderwaffe_server.service

import org.example.financial_wunderwaffe_server.model.request.AssetView
import java.util.UUID

interface AssetService {
    fun findByUserUID(userUID: UUID): List<AssetView>
    fun create(assetView: AssetView): Long
}