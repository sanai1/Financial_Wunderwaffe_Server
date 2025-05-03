package org.example.financial_wunderwaffe_server.database.entity

import jakarta.persistence.*
import org.example.financial_wunderwaffe_server.model.request.AssetTransactionView

@Entity
@Table(name = "asset_transaction")
data class AssetTransactionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    val asset: AssetEntity,

    @Column(name = "is_sale", nullable = false)
    val isSale: Boolean,

    @Column(nullable = false)
    val amount: Long,

    @Column(nullable = false)
    val date: String,
) {
    fun toAssetTransactionView(): AssetTransactionView =
        AssetTransactionView(
            id = id,
            assetID = asset.id,
            isSale = isSale,
            amount = amount,
            date = date
        )
}
