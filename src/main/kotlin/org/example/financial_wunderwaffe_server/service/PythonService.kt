package org.example.financial_wunderwaffe_server.service

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.example.financial_wunderwaffe_server.service.retrofit.python.PythonAPI
import org.example.financial_wunderwaffe_server.service.retrofit.python.PythonSingletonAPI
import org.example.financial_wunderwaffe_server.service.retrofit.python.model.Percentage
import org.springframework.stereotype.Service

@Service
class PythonService (
    val pythonAPI: PythonAPI = PythonSingletonAPI.getAPI()
){

    fun testToPython(login: String): Percentage {
        val percentage = Percentage(1, "Random Test Title")
        val answer = PythonSingletonAPI.getAPI().getPercentage(percentage)
        val parser = CoroutineScope(Dispatchers.IO).launch {
            // TODO: сделать реализацию без Coroutine или сделать ожидание ее завершения
        }
//        parser.join()
        return percentage
    }

}