package org.example.financial_wunderwaffe_server.controller

import org.example.financial_wunderwaffe_server.model.request.QuestionView
import org.example.financial_wunderwaffe_server.service.QuestionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class QuestionController(
    private val questionService: QuestionService
) {
    @GetMapping("/question")
    fun getAllQuestions() = questionService.findAll()

    @PostMapping("/admin/question")
    fun createQuestion(@RequestBody question: QuestionView) = questionService.create(question)

    @PutMapping("/admin/question")
    fun updateQuestionById(@RequestBody question: QuestionView) = questionService.update(question)

    @DeleteMapping("/admin/question")
    fun deleteQuestionById(@RequestParam questionId: Long) = questionService.delete(questionId)
}