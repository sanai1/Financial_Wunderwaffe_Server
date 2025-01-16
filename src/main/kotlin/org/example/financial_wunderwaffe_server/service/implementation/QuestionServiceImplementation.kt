package org.example.financial_wunderwaffe_server.service.implementation

import org.example.financial_wunderwaffe_server.database.repository.QuestionRepository
import org.example.financial_wunderwaffe_server.model.request.QuestionView
import org.example.financial_wunderwaffe_server.service.AnswerService
import org.example.financial_wunderwaffe_server.service.QuestionService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class QuestionServiceImplementation (
    private val questionRepository: QuestionRepository,
    private val answerService: AnswerService
): QuestionService {

    override fun findAll(): List<QuestionView> =
        questionRepository.findAll().map {
            it.toQuestionView(
                answerService.findByQuestionID(it.id)
            )
        }

    override fun create(questionView: QuestionView): Long {
        val id = questionRepository.save(questionView.toQuestionEntity()).id
        questionView.listAnswers.forEach {
            it.questionID = id
            answerService.create(it)
        }
        return id
    }

    override fun update(questionView: QuestionView): Boolean {
        val question = questionRepository.findByIdOrNull(questionView.id)
        return if (question != null) {
            val answers = answerService.findByQuestionID(question.id)
            for (answer in questionView.listAnswers) {
                var checkID = false
                answers.forEach {
                    if (it.id == answer.id) checkID = true
                }
                if (!checkID) {
                    return false
                }
            }
            questionView.listAnswers.forEach {
                answerService.update(it)
            }
            questionRepository.save(questionView.toQuestionEntity(questionView.id))
            true
        } else false
    }

    override fun delete(questionID: Long): Boolean {
        val question = questionRepository.findByIdOrNull(questionID)
        return if (question != null) {
            question.isEnabled = false
            questionRepository.save(question)
            true
        } else false
    }

}