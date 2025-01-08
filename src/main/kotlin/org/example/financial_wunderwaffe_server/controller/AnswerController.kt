package org.example.financial_wunderwaffe_server.controller

import org.example.financial_wunderwaffe_server.model.view.AnswerView
import org.example.financial_wunderwaffe_server.service.AnswerService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class AnswerController(
    private val answerService: AnswerService
) {

    @GetMapping("/answers")
    fun getAllAnswers(): List<AnswerView> =
        answerService.findAllAnswers()

    @GetMapping("/answers/questionId")
    fun getAnswersByQuestionId(@RequestParam questionId: Long): List<AnswerView> =
        answerService.findAnswersByQuestionId(questionId)

    @PostMapping("/admin/answer")
    fun createAnswer(@RequestBody answerView: AnswerView): Long =
        answerService.createAnswer(answerView)

    @PutMapping("/admin/answer")
    fun updateAnswer(@RequestBody answerView: AnswerView): Boolean =
        answerService.updateAnswer(answerView)

}