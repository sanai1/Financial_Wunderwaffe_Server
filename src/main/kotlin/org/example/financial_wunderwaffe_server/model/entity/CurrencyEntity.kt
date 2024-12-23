package org.example.financial_wunderwaffe_server.model.entity

import jakarta.persistence.*
import org.example.financial_wunderwaffe_server.model.view.CurrencyView

@Entity
@Table(name = "Currency")
data class CurrencyEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false, unique = true)
    val token: String,
) {
    fun toCurrencyView(): CurrencyView =
        CurrencyView(
            id = id,
            token = token
        )
}
