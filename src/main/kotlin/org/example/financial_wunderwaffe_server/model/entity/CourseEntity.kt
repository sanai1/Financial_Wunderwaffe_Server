package org.example.financial_wunderwaffe_server.model.entity

import jakarta.persistence.*
import org.example.financial_wunderwaffe_server.model.view.CourseView

@Entity
@Table(name = "Course")
data class CourseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne
    @JoinColumn(name = "currency_one_id", nullable = false)
    val currencyOne: CurrencyEntity,

    @ManyToOne
    @JoinColumn(name = "currency_two_id", nullable = false)
    val currencyTwo: CurrencyEntity,

    @Column(nullable = false)
    val course: Long
) {
    fun toCourseView(): CourseView =
        CourseView(
            id = id,
            currencyOne = currencyOne.token,
            currencyTwo = currencyTwo.token,
            course = course
        )
}