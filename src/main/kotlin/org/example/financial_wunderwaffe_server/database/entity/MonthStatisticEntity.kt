package org.example.financial_wunderwaffe_server.database.entity

import jakarta.persistence.*
import org.example.financial_wunderwaffe_server.model.request.MonthStatisticView

@Entity
@Table(name = "Month_statistic")
data class MonthStatisticEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne
    @JoinColumn(name = "user_uid", nullable = false)
    val user: UserEntity,

    @Column(nullable = false)
    val date: String,

    @Column(name = "all_expense", nullable = false)
    val allExpense: Long,

    @Column(name = "all_income", nullable = false)
    val allIncome: Long,

    @Column(name = "huge_expense", nullable = false)
    val hugeExpense: Long,

    @Column(name = "huge_income", nullable = false)
    val hugeIncome: Long
) {
    fun toMonthStatisticView(): MonthStatisticView =
        MonthStatisticView(
            id = id,
            userUID = user.uid,
            date = date,
            allExpense = allExpense,
            allIncome = allIncome,
            hugeExpense = hugeExpense,
            hugeIncome = hugeIncome
        )
}