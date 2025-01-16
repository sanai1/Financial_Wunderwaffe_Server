package org.example.financial_wunderwaffe_server.service

import org.example.financial_wunderwaffe_server.model.request.CalculationShareOfAssetView
import org.example.financial_wunderwaffe_server.model.response.ResponseTemplate
import java.util.UUID

interface CalculationShareOfAssetService {
    fun findByUserUID(userUID: UUID): ResponseTemplate<CalculationShareOfAssetView>
}