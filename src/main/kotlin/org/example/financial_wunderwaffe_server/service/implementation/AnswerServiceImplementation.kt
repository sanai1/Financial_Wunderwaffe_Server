package org.example.financial_wunderwaffe_server.service.implementation

import org.example.financial_wunderwaffe_server.database.repository.AnswerRepository
import org.example.financial_wunderwaffe_server.database.repository.QuestionRepository
import org.example.financial_wunderwaffe_server.model.request.AnswerView
import org.example.financial_wunderwaffe_server.service.AnswerService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AnswerServiceImplementation (
    val answerRepository: AnswerRepository,
    val questionRepository: QuestionRepository
): AnswerService {

    override fun findAll(): List<AnswerView> =
        answerRepository.findAll().map { it.toAnswerView() }

    override fun findByQuestionID(questionID: Long): List<AnswerView> {
        val question = questionRepository.findByIdOrNull(questionID)
        return if (question != null) {
            answerRepository.findAllByQuestion(question).map {
                it.toAnswerView()
            }
        } else emptyList()
    }

    override fun create(answerView: AnswerView): Long {
        val question = questionRepository.findByIdOrNull(answerView.questionID)
        return if (question != null) {
            answerRepository.save(answerView.toAnswerEntity(question)).id
        } else 0
    }

    override fun update(answerView: AnswerView): Boolean {
        val answer = answerRepository.findByIdOrNull(answerView.id)
        return if (answer != null) {
            answerRepository.save(answerView.toAnswerEntity(answerView.id, answer.question))
            true
        } else false
    }

}