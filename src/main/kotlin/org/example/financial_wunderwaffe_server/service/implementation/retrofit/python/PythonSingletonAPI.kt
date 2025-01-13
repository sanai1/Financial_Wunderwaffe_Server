package org.example.financial_wunderwaffe_server.service.implementation.retrofit.python

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PythonSingletonAPI {

    private var retrofit: Retrofit? = null
    private var pythonAPI: PythonAPI? = null

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        retrofit = Retrofit.Builder()
            .baseUrl("http://127.0.0.1:52781")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        pythonAPI = retrofit!!.create(PythonAPI::class.java)
    }

    fun getAPI(): PythonAPI = pythonAPI!!

}