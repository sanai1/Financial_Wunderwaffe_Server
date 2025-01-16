package org.example.financial_wunderwaffe_server.controller

import org.example.financial_wunderwaffe_server.model.request.CategoryView
import org.example.financial_wunderwaffe_server.service.CategoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/category")
class CategoryController (
    private val categoryService: CategoryService
){
    @GetMapping
    fun getCategoryByUserUID(@RequestParam userUID: UUID) = categoryService.findByUserUID(userUID)

    @PostMapping
    fun createCategory(@RequestBody categoryView: CategoryView) = categoryService.create(categoryView)

    @PutMapping
    fun updateCategory(
        @RequestParam categoryID: Long,
        @RequestParam name: String
    ) = categoryService.update(categoryID, name)
}