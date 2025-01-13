package org.example.financial_wunderwaffe_server.model.request

import org.example.financial_wunderwaffe_server.database.entity.BalanceEntity
import org.example.financial_wunderwaffe_server.database.entity.CurrencyEntity
import org.example.financial_wunderwaffe_server.database.entity.UserEntity
import java.util.UUID

data class BalanceView(
    val id: Long,
    val userUID: UUID,
    val currency: String,
    val amount: Long
) {
    fun toBalanceEntity(user: UserEntity, currency: CurrencyEntity): BalanceEntity =
        BalanceEntity(
            user = user,
            currency = currency,
            amount = amount
        )

    fun toBalanceEntity(id: Long, user: UserEntity, currency: CurrencyEntity): BalanceEntity =
        BalanceEntity(
            id = id,
            user = user,
            currency = currency,
            amount = amount
        )
}
