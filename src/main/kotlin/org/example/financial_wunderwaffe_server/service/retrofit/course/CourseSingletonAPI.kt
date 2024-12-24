package org.example.financial_wunderwaffe_server.service.retrofit.course

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CourseSingletonAPI {

    private var retrofit: Retrofit? = null
    private var courseApi: CourseAPI? = null

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://www.cbr-xml-daily.ru")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        courseApi = retrofit!!.create(CourseAPI::class.java)

    }

    fun getAPI(): CourseAPI = courseApi!!

}