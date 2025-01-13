package org.example.financial_wunderwaffe_server.controller

import org.example.financial_wunderwaffe_server.model.request.QuestionView
import org.example.financial_wunderwaffe_server.service.implementation.QuestionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class QuestionController(
    private val questionService: QuestionService
) {

    @GetMapping("/question")
    fun getAllQuestions(): List<QuestionView> =
        questionService.findAllQuestions()

    @PostMapping("/admin/question")
    fun createQuestion(@RequestBody question: QuestionView): Long =
        questionService.createQuestion(question)

    @PutMapping("/admin/question")
    fun updateQuestionById(@RequestBody question: QuestionView): Boolean =
        questionService.updateQuestionById(question)

    @DeleteMapping("/admin/question")
    fun deleteQuestionById(@RequestParam questionId: Long): Boolean =
        questionService.deleteQuestionById(questionId)

}