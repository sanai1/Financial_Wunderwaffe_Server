package org.example.financial_wunderwaffe_server.controller

import org.example.financial_wunderwaffe_server.model.request.UserAnswerView
import org.example.financial_wunderwaffe_server.service.implementation.UserAnswerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/user_answers")
class UserAnswerController(
    private val userAnswerService: UserAnswerService
) {

    @GetMapping
    fun getAllUserAnswersByUserUID(@RequestParam userUID: UUID): List<UserAnswerView> =
        userAnswerService.getAllUserAnswerByUserUID(userUID)

    @PostMapping
    fun createUserAnswers(@RequestBody listUserAnswersView: List<UserAnswerView>): List<Long> =
        userAnswerService.createUserAnswers(listUserAnswersView)

    @PutMapping
    fun updateUserAnswers(@RequestBody listUserAnswersView: List<UserAnswerView>): Boolean =
        userAnswerService.updateUserAnswers(listUserAnswersView)

}