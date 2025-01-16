package org.example.financial_wunderwaffe_server.service

import org.example.financial_wunderwaffe_server.model.request.CategoryView
import java.util.*

interface CategoryService {
    fun findByUserUID(userUID: UUID): List<CategoryView>
    fun create(categoryView: CategoryView): Long
    fun update(categoryID: Long, name: String): Boolean
}