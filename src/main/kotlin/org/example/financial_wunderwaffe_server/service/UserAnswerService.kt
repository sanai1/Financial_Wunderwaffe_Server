package org.example.financial_wunderwaffe_server.service

import org.example.financial_wunderwaffe_server.model.repository.AnswerRepository
import org.example.financial_wunderwaffe_server.model.repository.QuestionRepository
import org.example.financial_wunderwaffe_server.model.repository.UserAnswerRepository
import org.example.financial_wunderwaffe_server.model.repository.UserRepository
import org.example.financial_wunderwaffe_server.model.view.UserAnswerView
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserAnswerService(
    private val userAnswerRepository: UserAnswerRepository,
    private val userRepository: UserRepository,
    private val questionRepository: QuestionRepository,
    private val answerRepository: AnswerRepository
) {

    fun getAllUserAnswerByUserUID(userUID: UUID): List<UserAnswerView> =
        userAnswerRepository.findAllByUser(
            userRepository.findById(userUID).get()
        ).map {
            it.toUserAnswerView()
        }

    fun createUserAnswers(listUserAnswers: List<UserAnswerView>): List<Long> =
        listUserAnswers.map {
            val user = userRepository.findByIdOrNull(it.userUID)
            val question = questionRepository.findByIdOrNull(it.questionID)
            val answer = answerRepository.findByIdOrNull(it.answerID)
            if (user != null && question != null && answer != null) {
                it.toUserAnswerEntity(user, question, answer)
            } else return emptyList()
        }.map {
            userAnswerRepository.save(it).id
        }

    fun updateUserAnswers(listUserAnswers: List<UserAnswerView>): Boolean {
        listUserAnswers.map {
            val userAnswer = userAnswerRepository.findByIdOrNull(it.id)
            if (userAnswer != null) {
                it.toUserAnswerEntity(userAnswer.id, userAnswer.user, userAnswer.question, userAnswer.answer)
            } else return false
        }.map {
            userAnswerRepository.save(it)
        }
        return true
    }

}