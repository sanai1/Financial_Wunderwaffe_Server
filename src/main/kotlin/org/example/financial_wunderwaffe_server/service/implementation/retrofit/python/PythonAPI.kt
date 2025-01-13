package org.example.financial_wunderwaffe_server.service.implementation.retrofit.python

import org.example.financial_wunderwaffe_server.model.request.UserAnswerView
import org.example.financial_wunderwaffe_server.service.implementation.retrofit.python.model.Percentage
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PythonAPI {

    @POST("/process") // TODO: поставить актуальный путь
    fun getPercentage(
        @Body body: List<UserAnswerView>
    ): Call<List<Percentage>>

}