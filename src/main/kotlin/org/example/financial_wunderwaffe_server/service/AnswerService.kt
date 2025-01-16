package org.example.financial_wunderwaffe_server.service

import org.example.financial_wunderwaffe_server.model.request.AnswerView

interface AnswerService {
    fun findAll(): List<AnswerView>
    fun findByQuestionID(questionID: Long): List<AnswerView>
    fun create(answerView: AnswerView): Long
    fun update(answerView: AnswerView): Boolean
}