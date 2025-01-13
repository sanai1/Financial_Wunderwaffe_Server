package org.example.financial_wunderwaffe_server.model.request

import org.example.financial_wunderwaffe_server.database.entity.CategoryEntity
import org.example.financial_wunderwaffe_server.database.entity.TransactionEntity
import org.example.financial_wunderwaffe_server.database.entity.UserEntity
import java.util.UUID

data class TransactionView(
    val id: Long,
    val userUID: UUID,
    val categoryID: Long,
    val amount: Long,
    val date: String,
    val type: Boolean,
    val description: String
) {
    fun toTransactionEntity(user: UserEntity, category: CategoryEntity): TransactionEntity =
        TransactionEntity(
            user = user,
            category = category,
            amount = amount,
            date = date,
            type = type,
            description = description
        )

    fun toTransactionEntity(id: Long, user: UserEntity, category: CategoryEntity): TransactionEntity =
        TransactionEntity(
            id = id,
            user = user,
            category = category,
            amount = amount,
            date = date,
            type = type,
            description = description
        )
}