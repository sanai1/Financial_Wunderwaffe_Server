package org.example.financial_wunderwaffe_server.model.view

import org.example.financial_wunderwaffe_server.model.entity.AnswerEntity
import org.example.financial_wunderwaffe_server.model.entity.QuestionEntity
import org.example.financial_wunderwaffe_server.model.entity.UserAnswerEntity
import org.example.financial_wunderwaffe_server.model.entity.UserEntity
import java.util.UUID

data class UserAnswerView(
    var id: Long,
    val userUID: UUID,
    val questionID: Long,
    val answerID: Long,
    val date: String
) {
    fun toUserAnswerEntity(user: UserEntity, question: QuestionEntity, answer: AnswerEntity): UserAnswerEntity =
        UserAnswerEntity(
            user = user,
            question = question,
            answer = answer,
            date = date
        )
    fun toUserAnswerEntity(id: Long, user: UserEntity, question: QuestionEntity, answer: AnswerEntity): UserAnswerEntity =
        UserAnswerEntity(
            id = id,
            user = user,
            question = question,
            answer = answer,
            date = date
        )

}
