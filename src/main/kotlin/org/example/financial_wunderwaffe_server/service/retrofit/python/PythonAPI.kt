package org.example.financial_wunderwaffe_server.service.retrofit.python

import org.example.financial_wunderwaffe_server.service.retrofit.python.model.Percentage
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PythonAPI {
    @POST("/process")
    fun getPercentage(@Body body: Percentage): Call<Percentage>
}