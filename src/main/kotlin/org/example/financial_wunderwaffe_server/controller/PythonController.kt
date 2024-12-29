package org.example.financial_wunderwaffe_server.controller

import org.example.financial_wunderwaffe_server.service.PythonService
import org.example.financial_wunderwaffe_server.service.retrofit.python.model.Percentage
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1")
class PythonController (
    val pythonService: PythonService
){

    @GetMapping("/admin/python/percentage")
    fun toPython(@RequestParam login: String): Percentage =
        pythonService.testToPython(login)

}