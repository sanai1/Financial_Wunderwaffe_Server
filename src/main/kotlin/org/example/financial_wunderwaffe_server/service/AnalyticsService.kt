package org.example.financial_wunderwaffe_server.service

import org.example.financial_wunderwaffe_server.model.request.BudgetAnalyticsView
import org.example.financial_wunderwaffe_server.model.request.CapitalAnalyticsView
import java.util.UUID

interface AnalyticsService {
    fun getBudgetByMonth(userUID: UUID): List<BudgetAnalyticsView>
    fun getCapitalByMonth(userUID: UUID): List<CapitalAnalyticsView>
}