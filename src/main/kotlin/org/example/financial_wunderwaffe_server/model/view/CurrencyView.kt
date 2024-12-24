package org.example.financial_wunderwaffe_server.model.view

import org.example.financial_wunderwaffe_server.model.entity.CurrencyEntity

data class CurrencyView(
    val id: Long,
    val token: String
) {
    fun toCurrencyEntity(): CurrencyEntity =
        CurrencyEntity(
            token = token
        )

    fun toCurrencyEntity(id: Long): CurrencyEntity =
        CurrencyEntity(
            id = id,
            token = token
        )

}
