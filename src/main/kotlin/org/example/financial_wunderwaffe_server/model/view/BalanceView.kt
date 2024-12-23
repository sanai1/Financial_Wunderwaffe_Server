package org.example.financial_wunderwaffe_server.model.view

import org.example.financial_wunderwaffe_server.model.entity.BalanceEntity
import org.example.financial_wunderwaffe_server.model.entity.CurrencyEntity
import org.example.financial_wunderwaffe_server.model.entity.UserEntity
import java.util.UUID

data class BalanceView(
    val id: Long,
    val userUID: UUID,
    val currency: String,
    val amount: Long
) {
    fun toBalanceEntity(user: UserEntity, currency: CurrencyEntity): BalanceEntity =
        BalanceEntity(
            id = id,
            user = user,
            currency = currency,
            amount = amount
        )
}
