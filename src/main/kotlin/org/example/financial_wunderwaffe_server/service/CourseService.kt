package org.example.financial_wunderwaffe_server.service

import org.example.financial_wunderwaffe_server.model.entity.CourseEntity
import org.example.financial_wunderwaffe_server.model.entity.CurrencyEntity
import org.example.financial_wunderwaffe_server.model.repository.CourseRepository
import org.example.financial_wunderwaffe_server.model.repository.CurrencyRepository
import org.example.financial_wunderwaffe_server.model.view.CourseView
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CourseService (
    private val courseRepository: CourseRepository,
    private val currencyRepository: CurrencyRepository
){

    fun getAllCourse(): List<CourseView> =
        courseRepository.findAll().map { it.toCourseView() }

    fun createCourse(courseView: CourseView): CourseView? {
        var currencyOne: CurrencyEntity? = null
        var currencyTwo: CurrencyEntity? = null
        currencyRepository.findAll().forEach {
            if (it.token == courseView.currencyOne)
                currencyOne = it
            else if (it.token == courseView.currencyTwo)
                currencyTwo = it
        }
        return if (currencyOne != null && currencyTwo != null)
            courseRepository.save(
                CourseEntity(
                    currencyOne = currencyOne!!,
                    currencyTwo = currencyTwo!!,
                    course = courseView.course
                )
            ).toCourseView()
        else null
    }

    fun deleteCourse(courseID: Long): Boolean {
        return if (courseRepository.findByIdOrNull(courseID) != null) {
            courseRepository.deleteById(courseID)
            true
        } else false
    }

}