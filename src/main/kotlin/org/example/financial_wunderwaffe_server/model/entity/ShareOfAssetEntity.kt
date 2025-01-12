package org.example.financial_wunderwaffe_server.model.entity

import jakarta.persistence.*
import org.example.financial_wunderwaffe_server.model.view.ShareOfAssetView

@Entity
@Table(name = "share_of_asset")
data class ShareOfAssetEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @ManyToOne
    @JoinColumn(name = "calculation_share_of_asset_id", nullable = false)
    val calculationShareOfAsset: CalculationShareOfAssetEntity,

    @ManyToOne
    @JoinColumn(name = "type_asset", nullable = false)
    val typeAsset: TypeAssetEntity,

    @Column(nullable = false)
    val share: Long
) {
    fun toShareOfAssetView(): ShareOfAssetView =
        ShareOfAssetView(
            id = id,
            typeAsset = typeAsset.toTypeAssetView(),
            share = share
        )
}