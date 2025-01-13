package org.example.financial_wunderwaffe_server.model.request

import org.example.financial_wunderwaffe_server.database.entity.AnswerEntity
import org.example.financial_wunderwaffe_server.database.entity.QuestionEntity

data class AnswerView(
    val id: Long,
    val text: String,
    var questionID: Long = 0L
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