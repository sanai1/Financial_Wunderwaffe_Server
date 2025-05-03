package org.example.financial_wunderwaffe_server.service

import org.example.financial_wunderwaffe_server.model.request.AssetInformationView
import org.example.financial_wunderwaffe_server.model.request.AssetPriceView
import org.example.financial_wunderwaffe_server.model.request.AssetTransactionView

interface AssetInformationService {
    fun createAssetPrice(assetPriceView: AssetPriceView): Long
    fun createAssetTransaction(assetTransactionView: AssetTransactionView): Long
    fun findAssetInformationByAsset(assetId: Long): List<AssetInformationView>
}