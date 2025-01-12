package org.example.financial_wunderwaffe_server.model.entity

import jakarta.persistence.*
import org.example.financial_wunderwaffe_server.model.view.CalculationShareOfAssetView
import org.example.financial_wunderwaffe_server.model.view.ShareOfAssetView
import java.time.LocalDate

@Entity
@Table(name = "calculation_share_of_asset")
data class CalculationShareOfAssetEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @ManyToOne
    @JoinColumn(name = "user_uid", nullable = false)
    val user: UserEntity,

    @Column(nullable = false)
    val date: LocalDate
) {
    fun toCalculationShareOfAssetView(listShareOfAsset: List<ShareOfAssetView>): CalculationShareOfAssetView =
        CalculationShareOfAssetView(
            id = id,
            userUID = user.uid,
            date = date,
            listShareOfAsset = listShareOfAsset
        )
}