package org.example.financial_wunderwaffe_server.model.view

import org.example.financial_wunderwaffe_server.model.entity.CourseEntity
import org.example.financial_wunderwaffe_server.model.entity.CurrencyEntity

data class CourseView(
    val id: Long,
    val currencyOne: String,
    val currencyTwo: String,
    val course: Long
) {
    fun toCourseEntity(currencyOne: CurrencyEntity, currencyTwo: CurrencyEntity): CourseEntity =
        CourseEntity(
            id = id,
            currencyOne = currencyOne,
            currencyTwo = currencyTwo,
            course = course
        )
}
