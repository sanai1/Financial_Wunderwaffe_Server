package org.example.financial_wunderwaffe_server.service.implementation.retrofit.course

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CourseSingletonAPI {

    private var retrofit: Retrofit? = null
    private var courseApi: CourseAPI? = null

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        retrofit = Retrofit.Builder()
            .baseUrl("https://www.cbr-xml-daily.ru")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        courseApi = retrofit!!.create(CourseAPI::class.java)

    }

    fun getAPI(): CourseAPI = courseApi!!

}