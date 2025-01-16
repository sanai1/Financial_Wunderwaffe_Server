package org.example.financial_wunderwaffe_server.service

import org.example.financial_wunderwaffe_server.model.request.QuestionView

interface QuestionService {
    fun findAll(): List<QuestionView>
    fun create(questionView: QuestionView): Long
    fun update(questionView: QuestionView): Boolean
    fun delete(questionID: Long): Boolean
}