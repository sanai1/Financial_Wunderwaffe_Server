package org.example.financial_wunderwaffe_server.model.repository

import org.example.financial_wunderwaffe_server.model.entity.CategoryEntity
import org.example.financial_wunderwaffe_server.model.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository: JpaRepository<CategoryEntity, Long> {
    fun findCategoryByUser(user: UserEntity): List<CategoryEntity>
}