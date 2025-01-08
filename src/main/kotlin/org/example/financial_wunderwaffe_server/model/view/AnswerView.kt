package org.example.financial_wunderwaffe_server.model.view

import org.example.financial_wunderwaffe_server.model.entity.AnswerEntity
import org.example.financial_wunderwaffe_server.model.entity.QuestionEntity

data class AnswerView(
    val id: Long,
    val text: String,
    val questionID: Long
) {
    fun toAnswerEntity(question: QuestionEntity): AnswerEntity =
        AnswerEntity(
            text = text,
            question = question
        )
    fun toAnswerEntity(id: Long, question: QuestionEntity): AnswerEntity =
        AnswerEntity(
            id = id,
            text = text,
            question = question
        )
}