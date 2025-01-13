package org.example.financial_wunderwaffe_server.model.request

import org.example.financial_wunderwaffe_server.database.entity.CurrencyEntity

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
