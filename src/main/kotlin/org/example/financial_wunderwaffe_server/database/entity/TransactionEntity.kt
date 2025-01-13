package org.example.financial_wunderwaffe_server.database.entity

import jakarta.persistence.*
import org.example.financial_wunderwaffe_server.model.request.TransactionView

@Entity
@Table(name = "Transaction")
data class TransactionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @ManyToOne
    @JoinColumn(name = "user_uid", nullable = false)
    val user: UserEntity,

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    val category: CategoryEntity,

    @Column(nullable = false)
    val amount: Long,

    @Column(nullable = false)
    val date: String,

    @Column(nullable = false)
    val type: Boolean,

    @Column
    val description: String
) {
    fun toTransactionView(): TransactionView =
        TransactionView(
            id = id,
            userUID = user.uid,
            categoryID = category.id,
            amount = amount,
            date = date,
            type = type,
            description = description
        )
}