package org.example.financial_wunderwaffe_server.service

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.example.financial_wunderwaffe_server.model.entity.CourseEntity
import org.example.financial_wunderwaffe_server.model.entity.CurrencyEntity
import org.example.financial_wunderwaffe_server.model.repository.CourseRepository
import org.example.financial_wunderwaffe_server.model.repository.CurrencyRepository
import org.example.financial_wunderwaffe_server.model.view.CourseView
import org.example.financial_wunderwaffe_server.service.retrofit.course.CourseSingletonAPI
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

    fun checkCourseWithAPI() =
        CoroutineScope(Dispatchers.IO).launch {
            courseRepository.findAll().map { it ->
                val token = it.currencyTwo.token
                var newValue = 0L
                CourseSingletonAPI.getAPI().getCourse().Valute.forEach {
                    if (it.value.CharCode == token)
                        newValue = (it.value.Value * 10_000).toLong()
                }
                if (newValue != 0L) {
                    it.course = newValue
                    courseRepository.save(it)
                }
            }
        }

}