package org.example.financial_wunderwaffe_server.controller

import org.example.financial_wunderwaffe_server.model.request.BudgetAnalyticsView
import org.example.financial_wunderwaffe_server.model.request.CapitalAnalyticsView
import org.example.financial_wunderwaffe_server.service.AnalyticsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/analytics")
class AnalyticsController(
    private val analyticsService: AnalyticsService,
) {
    @GetMapping("/budget")
    fun getBudgetByMonth(@RequestParam userUID: UUID): List<BudgetAnalyticsView> =
        analyticsService.getBudgetByMonth(userUID)

    @GetMapping("/capital")
    fun getCapitalByMonth(@RequestParam userUID: UUID): List<CapitalAnalyticsView> =
        analyticsService.getCapitalByMonth(userUID)
}