package org.example.financial_wunderwaffe_server.service

import org.example.financial_wunderwaffe_server.model.repository.CategoryRepository
import org.example.financial_wunderwaffe_server.model.repository.UserRepository
import org.example.financial_wunderwaffe_server.model.view.CategoryView
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryService (
    private val categoryRepository: CategoryRepository,
    private val userRepository: UserRepository
){

    fun getCategoryByUserUID(userUID: UUID): List<CategoryView> =
        categoryRepository.findCategoryByUser(
            userRepository.findById(userUID).get()
        ).map { it.toCategoryView() }

    fun createCategory(categoryView: CategoryView): CategoryView? {
        val user = userRepository.findByIdOrNull(categoryView.userUID)
        return if (user != null) categoryRepository.save(categoryView.toCategoryEntity(user)).toCategoryView()
        else null
    }

    fun updateCategory(categoryView: CategoryView): CategoryView? {
        val user = userRepository.findByIdOrNull(categoryView.userUID)
        val category = categoryRepository.findByIdOrNull(categoryView.id)
        return if (user != null && category != null)
            categoryRepository.save(
                categoryView.toCategoryEntity(categoryView.id, user)
            ).toCategoryView()
        else null
    }

}