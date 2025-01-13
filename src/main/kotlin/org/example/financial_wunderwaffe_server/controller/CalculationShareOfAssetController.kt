package org.example.financial_wunderwaffe_server.controller

import org.example.financial_wunderwaffe_server.model.ResponseTemplate
import org.example.financial_wunderwaffe_server.model.view.CalculationShareOfAssetView
import org.example.financial_wunderwaffe_server.service.CalculationShareOfAssetService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/calculation_share_of_asset")
class CalculationShareOfAssetController(
    private val calculationShareOfAssetService: CalculationShareOfAssetService
) {

    @GetMapping
    fun getCalculationShareOfAsset(@RequestParam userUID: UUID): ResponseTemplate<CalculationShareOfAssetView> =
        calculationShareOfAssetService.getCalculationShareOfAssetsByUserUID(userUID)

}