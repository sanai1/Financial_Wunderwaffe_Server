package org.example.financial_wunderwaffe_server.service

import org.example.financial_wunderwaffe_server.model.repository.QuestionRepository
import org.example.financial_wunderwaffe_server.model.view.QuestionView
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class QuestionService(
    private val questionRepository: QuestionRepository
) {

    fun findAllQuestions(): List<QuestionView> =
        questionRepository.findAll().map {
            it.toQuestionView()
        }

    fun createQuestion(questionView: QuestionView): Long =
        questionRepository.save(questionView.toQuestionEntity()).id

    fun updateQuestionById(questionView: QuestionView): Boolean {
        val question = questionRepository.findByIdOrNull(questionView.id)
        return if (question != null) {
            questionRepository.save(questionView.toQuestionEntity(questionView.id))
            true
        } else false
    }

    fun deleteQuestionById(questionId: Long): Boolean {
        val question = questionRepository.findByIdOrNull(questionId)
        return if (question != null) {
            question.isEnabled = false
            questionRepository.save(question)
            true
        } else false
    }

}