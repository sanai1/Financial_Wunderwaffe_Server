package org.example.financial_wunderwaffe_server.model.entity

import jakarta.persistence.*
import org.example.financial_wunderwaffe_server.model.view.CategoryView

@Entity
@Table(name = "Category")
data class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    val type: Boolean,

    @ManyToOne
    @JoinColumn(nullable = false)
    val user: UserEntity
) {
    fun toCategoryView(): CategoryView =
        CategoryView(
            id = id,
            name = name,
            type = type,
            userUID = user.uid,
        )
}