package org.example.financial_wunderwaffe_server.database.entity

import jakarta.persistence.*
import org.example.financial_wunderwaffe_server.model.request.AssetView

@Entity
@Table(name = "asset")
data class AssetEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @ManyToOne
    @JoinColumn(name = "user_uid", nullable = false)
    val user: UserEntity,

    @Column(nullable = false)
    val title: String,
) {
    fun toAssetView(amount: Long): AssetView =
        AssetView(
            id = id,
            userUID = user.uid,
            title = title,
            amount = amount
        )
}
