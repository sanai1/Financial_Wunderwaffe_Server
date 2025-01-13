package org.example.financial_wunderwaffe_server.model.request

import org.example.financial_wunderwaffe_server.database.entity.AnswerEntity
import org.example.financial_wunderwaffe_server.database.entity.QuestionEntity
import org.example.financial_wunderwaffe_server.database.entity.UserAnswerEntity
import org.example.financial_wunderwaffe_server.database.entity.UserEntity
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
