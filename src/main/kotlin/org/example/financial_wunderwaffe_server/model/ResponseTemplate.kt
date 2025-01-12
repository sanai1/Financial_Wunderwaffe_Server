package org.example.financial_wunderwaffe_server.model

data class ResponseTemplate<T>(
    val status: Status,
    val data: T?
)