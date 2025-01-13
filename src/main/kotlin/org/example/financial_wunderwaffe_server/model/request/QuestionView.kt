package org.example.financial_wunderwaffe_server.model.request

import org.example.financial_wunderwaffe_server.database.entity.QuestionEntity

data class QuestionView(
    val id: Long,
    val text: String,
    val isEnabled: Boolean,
    val listAnswers: List<AnswerView>
) {
    fun toQuestionEntity(): QuestionEntity =
        QuestionEntity(
            text = text,
            isEnabled = isEnabled
        )
    fun toQuestionEntity(id: Long): QuestionEntity =
        QuestionEntity(
            id = id,
            text = text,
            isEnabled = isEnabled
        )
}
