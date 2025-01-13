package org.example.financial_wunderwaffe_server.service.implementation

import org.example.financial_wunderwaffe_server.database.repository.QuestionRepository
import org.example.financial_wunderwaffe_server.model.request.QuestionView
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class QuestionService(
    private val questionRepository: QuestionRepository,
    private val answerService: AnswerService
) {

    fun findAllQuestions(): List<QuestionView> =
        questionRepository.findAll().map {
            it.toQuestionView(
                answerService.findAnswersByQuestionId(it.id)
            )
        }

    fun createQuestion(questionView: QuestionView): Long {
        val id = questionRepository.save(questionView.toQuestionEntity()).id
        questionView.listAnswers.forEach {
            it.questionID = id
            answerService.createAnswer(it)
        }
        return id
    }

    fun updateQuestionById(questionView: QuestionView): Boolean {
        val question = questionRepository.findByIdOrNull(questionView.id)
        return if (question != null) {
            val answers = answerService.findAnswersByQuestionId(question.id)
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
                answerService.updateAnswer(it)
            }
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