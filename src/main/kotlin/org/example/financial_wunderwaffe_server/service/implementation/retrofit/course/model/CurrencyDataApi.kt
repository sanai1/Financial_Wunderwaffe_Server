package org.example.financial_wunderwaffe_server.service.implementation.retrofit.course.model

data class CurrencyDataApi(
    val ID: String,
    val NumCode: String,
    val CharCode: String,
    val Nominal: Long,
    val Name: String,
    val Value: Double,
    val Previous: Double
)