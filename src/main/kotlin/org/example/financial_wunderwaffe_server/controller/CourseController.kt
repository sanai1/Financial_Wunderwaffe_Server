package org.example.financial_wunderwaffe_server.controller

import org.example.financial_wunderwaffe_server.model.view.CourseView
import org.example.financial_wunderwaffe_server.service.CourseService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class CourseController (
    private val courseService: CourseService
){

    @GetMapping("/course")
    fun getAllCourse(): List<CourseView> =
        courseService.getAllCourse()

    @PostMapping("/admin/course")
    fun createCourse(@RequestBody courseView: CourseView): CourseView? =
        courseService.createCourse(courseView)

    @DeleteMapping("/admin/course")
    fun deleteCourse(@RequestParam courseID: Long): Boolean =
        courseService.deleteCourse(courseID)

}