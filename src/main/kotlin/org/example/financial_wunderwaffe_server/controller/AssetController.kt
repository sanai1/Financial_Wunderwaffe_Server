package org.example.financial_wunderwaffe_server.controller

import org.example.financial_wunderwaffe_server.model.request.AssetView
import org.example.financial_wunderwaffe_server.service.AssetService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/assets")
class AssetController(
    private val assetService: AssetService,
) {
    @GetMapping
    fun findAssetsByUserUID(@RequestParam userUID: UUID) = assetService.findByUserUID(userUID)

    @PostMapping
    fun createAsset(@RequestBody assetView: AssetView) = assetService.create(assetView)
}