package org.example.financial_wunderwaffe_server.service.implementation

import org.example.financial_wunderwaffe_server.database.repository.CategoryRepository
import org.example.financial_wunderwaffe_server.database.repository.UserRepository
import org.example.financial_wunderwaffe_server.model.request.CategoryView
import org.example.financial_wunderwaffe_server.service.CategoryService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoryServiceImplementation (
    private val categoryRepository: CategoryRepository,
    private val userRepository: UserRepository
): CategoryService {

    override fun findByUserUID(userUID: UUID): List<CategoryView> =
        categoryRepository.findCategoryByUser(
            userRepository.findById(userUID).get()
        ).map { it.toCategoryView() }

    override fun create(categoryView: CategoryView): Long {
        val user = userRepository.findByIdOrNull(categoryView.userUID)
        return if (user != null) categoryRepository.save(categoryView.toCategoryEntity(user)).id
        else 0
    }

    override fun update(categoryID: Long, name: String): Boolean {
        val category = categoryRepository.findByIdOrNull(categoryID)
        return if (category != null) {
            category.name = name
            categoryRepository.save(category)
            true
        } else false
    }

}