package org.example.financial_wunderwaffe_server.database.entity

import jakarta.persistence.*
import org.example.financial_wunderwaffe_server.model.request.TypeAssetView

@Entity
@Table(name = "type_asset")
data class TypeAssetEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(unique = true, nullable = false)
    val name: String,

    @ElementCollection
    val color: List<Int>
) {
    fun toTypeAssetView(): TypeAssetView =
        TypeAssetView(id, name, color)
}