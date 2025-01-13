package org.example.financial_wunderwaffe_server.service.implementation

import org.example.financial_wunderwaffe_server.database.repository.AnswerRepository
import org.example.financial_wunderwaffe_server.database.repository.QuestionRepository
import org.example.financial_wunderwaffe_server.model.request.AnswerView
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AnswerService(
    val answerRepository: AnswerRepository,
    val questionRepository: QuestionRepository
) {

    fun findAnswersByQuestionId(questionId: Long): List<AnswerView> {
        val question = questionRepository.findByIdOrNull(questionId)
        return if (question != null) {
            answerRepository.findAllByQuestion(question).map {
                it.toAnswerView()
            }
        } else emptyList()
    }

    fun createAnswer(answerView: AnswerView): Long {
        val question = questionRepository.findByIdOrNull(answerView.questionID)
        return if (question != null) {
            answerRepository.save(answerView.toAnswerEntity(question)).id
        } else 0
    }

    fun updateAnswer(answerView: AnswerView): Boolean {
        val answer = answerRepository.findByIdOrNull(answerView.id)
        return if (answer != null) {
            answerRepository.save(answerView.toAnswerEntity(answerView.id, answer.question))
            true
        } else false
    }

}