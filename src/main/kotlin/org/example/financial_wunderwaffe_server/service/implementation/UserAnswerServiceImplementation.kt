package org.example.financial_wunderwaffe_server.service.implementation

import org.example.financial_wunderwaffe_server.database.entity.UserAnswerEntity
import org.example.financial_wunderwaffe_server.database.repository.AnswerRepository
import org.example.financial_wunderwaffe_server.database.repository.QuestionRepository
import org.example.financial_wunderwaffe_server.database.repository.UserAnswerRepository
import org.example.financial_wunderwaffe_server.database.repository.UserRepository
import org.example.financial_wunderwaffe_server.model.request.UserAnswerView
import org.example.financial_wunderwaffe_server.service.UserAnswerService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserAnswerServiceImplementation (
    private val userAnswerRepository: UserAnswerRepository,
    private val userRepository: UserRepository,
    private val questionRepository: QuestionRepository,
    private val answerRepository: AnswerRepository
): UserAnswerService {

    override fun findByUserUID(userUID: UUID): List<UserAnswerView> =
        userAnswerRepository.findAllByUser(
            userRepository.findById(userUID).get()
        ).map {
            it.toUserAnswerView()
        }

    override fun create(listUserAnswerView: List<UserAnswerView>): List<Long> =
        listUserAnswerView.map {
            val user = userRepository.findByIdOrNull(it.userUID)
            val question = questionRepository.findByIdOrNull(it.questionID)
            val answer = answerRepository.findByIdOrNull(it.answerID)
            if (user != null && question != null && answer != null) {
                it.toUserAnswerEntity(user, question, answer)
            } else return emptyList()
        }.map {
            userAnswerRepository.save(it).id
        }

    override fun update(listUserAnswerView: List<UserAnswerView>): Boolean {
        val listForSave = mutableListOf<UserAnswerEntity>()
        listUserAnswerView.map {
            val userAnswer = userAnswerRepository.findByIdOrNull(it.id)
            val user = userRepository.findByIdOrNull(it.userUID)
            val question = questionRepository.findByIdOrNull(it.questionID)
            val answer = answerRepository.findByIdOrNull(it.answerID)
            if (userAnswer != null && user != null && question != null && answer != null) {
                listForSave.add(
                    it.toUserAnswerEntity(userAnswer.id, user, question, answer)
                )
            } else return false
        }
        listForSave.forEach {
            userAnswerRepository.save(it)
        }
        return true
    }

}