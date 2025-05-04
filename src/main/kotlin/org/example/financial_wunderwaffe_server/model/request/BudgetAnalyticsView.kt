package org.example.financial_wunderwaffe_server.model.request

data class BudgetAnalyticsView(
    val month: String,
    val listCategory: List<CategoryAnalyticsView>,
) {
    data class CategoryAnalyticsView(
        val id: Long,
        val title: String,
        val isIncome: Boolean,
        val isLarge: Boolean,
        val amount: Long,
    )
}
