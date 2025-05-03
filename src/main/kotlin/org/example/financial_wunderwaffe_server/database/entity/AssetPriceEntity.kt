package org.example.financial_wunderwaffe_server.database.entity

import jakarta.persistence.*
import org.example.financial_wunderwaffe_server.model.request.AssetPriceView

@Entity
@Table(name = "asset_price")
data class AssetPriceEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    val asset: AssetEntity,

    @Column(nullable = false)
    val date: String,

    @Column(name = "old_price", nullable = false)
    val oldPrice: Long,

    @Column(name = "current_price", nullable = false)
    val currentPrice: Long,
) {
    fun toAssetPriceView(): AssetPriceView =
        AssetPriceView(
            id = id,
            assetID = asset.id,
            date = date,
            oldPrice = oldPrice,
            currentPrice = currentPrice,
        )
}
