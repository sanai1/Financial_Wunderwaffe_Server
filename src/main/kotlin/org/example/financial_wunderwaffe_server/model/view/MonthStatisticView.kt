package org.example.financial_wunderwaffe_server.model.view

import org.example.financial_wunderwaffe_server.model.entity.MonthStatisticEntity
import org.example.financial_wunderwaffe_server.model.entity.UserEntity
import java.util.UUID

data class MonthStatisticView(
    val id: Long,
    val userUID: UUID,
    val date: String,
    val allExpense: Long,
    val allIncome: Long,
    val hugeExpense: Long,
    val hugeIncome: Long
) {
    fun toMonthStatisticEntity(user: UserEntity): MonthStatisticEntity =
        MonthStatisticEntity(
            id = id,
            user = user,
            date = date,
            allExpense = allExpense,
            allIncome = allIncome,
            hugeExpense = hugeExpense,
            hugeIncome = hugeIncome
        )
}
