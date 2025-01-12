package org.example.financial_wunderwaffe_server.model.entity

import jakarta.persistence.*
import org.example.financial_wunderwaffe_server.model.view.TypeAssetView

@Entity
@Table(name = "type_asset")
data class TypeAssetEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(unique = true, nullable = false)
    val name: String,

    @Column
    val description: String
) {
    fun toTypeAssetView(): TypeAssetView =
        TypeAssetView(id, name, description)
}