package org.example.financial_wunderwaffe_server.controller

import org.example.financial_wunderwaffe_server.model.request.AssetPriceView
import org.example.financial_wunderwaffe_server.model.request.AssetTransactionView
import org.example.financial_wunderwaffe_server.service.AssetInformationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/assets_information")
class AssetInformationController(
    private val assetInformationService: AssetInformationService,
) {
    @GetMapping("/{assetId}")
    fun findAssetInformationByAssetId(@PathVariable("assetId") assetId: Long) =
        assetInformationService.findAssetInformationByAsset(assetId)

    @PostMapping("/price")
    fun createAssetPrice(@RequestBody assetPriceView: AssetPriceView) =
        assetInformationService.createAssetPrice(assetPriceView)

    @PostMapping("/transaction")
    fun createAssetTransaction(@RequestBody assetTransactionView: AssetTransactionView) =
        assetInformationService.createAssetTransaction(assetTransactionView)
}