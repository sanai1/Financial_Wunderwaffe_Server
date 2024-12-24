package org.example.financial_wunderwaffe_server.model.view

import org.example.financial_wunderwaffe_server.model.entity.CategoryEntity
import org.example.financial_wunderwaffe_server.model.entity.UserEntity
import java.util.UUID

data class CategoryView(
    val id: Long,
    val name: String,
    val type: Boolean,
    val userUID: UUID
) {
    fun toCategoryEntity(user: UserEntity): CategoryEntity =
        CategoryEntity(
            name = name,
            type = type,
            user = user
        )

    fun toCategoryEntity(id: Long, user: UserEntity): CategoryEntity =
        CategoryEntity(
            id = id,
            name = name,
            type = type,
            user = user
        )
}
