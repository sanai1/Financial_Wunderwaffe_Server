package org.example.financial_wunderwaffe_server.service.implementation.retrofit.course.model

data class CourseDataApi(
    val Date: String,
    val PreviousDate: String,
    val PreviousURL: String,
    val Timestamp: String,
    val Valute: Map<String, CurrencyDataApi>
)
