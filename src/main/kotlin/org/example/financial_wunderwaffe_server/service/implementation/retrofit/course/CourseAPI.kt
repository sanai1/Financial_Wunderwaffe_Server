package org.example.financial_wunderwaffe_server.service.implementation.retrofit.course

import org.example.financial_wunderwaffe_server.service.implementation.retrofit.course.model.CourseDataApi
import retrofit2.http.GET

interface CourseAPI {
    @GET("/daily_json.js")
    suspend fun getCourse(): CourseDataApi
}