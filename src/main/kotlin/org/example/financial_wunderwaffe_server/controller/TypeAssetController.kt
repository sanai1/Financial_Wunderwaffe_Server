package org.example.financial_wunderwaffe_server.controller

import org.example.financial_wunderwaffe_server.model.request.TypeAssetView
import org.example.financial_wunderwaffe_server.service.TypeAssetService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class TypeAssetController(
    private val typeAssetsService: TypeAssetService
) {
    @GetMapping("/typeAssets")
    fun findAll(): List<TypeAssetView> = typeAssetsService.findAll()

    @PostMapping("/admin/typeAsset")
    fun createTypeAsset(@RequestBody typeAssetView: TypeAssetView) = typeAssetsService.create(typeAssetView)

    @PutMapping("/admin/typeAsset")
    fun updateTypeAssetByID(@RequestBody typeAssetView: TypeAssetView) = typeAssetsService.update(typeAssetView)
}