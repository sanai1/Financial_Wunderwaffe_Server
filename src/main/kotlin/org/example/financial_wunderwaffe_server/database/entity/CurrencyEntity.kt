package org.example.financial_wunderwaffe_server.database.entity

import jakarta.persistence.*
import org.example.financial_wunderwaffe_server.model.request.CurrencyView

@Entity
@Table(name = "Currency")
data class CurrencyEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(nullable = false, unique = true)
    val token: String,
) {
    fun toCurrencyView(): CurrencyView =
        CurrencyView(
            id = id,
            token = token
        )
}
