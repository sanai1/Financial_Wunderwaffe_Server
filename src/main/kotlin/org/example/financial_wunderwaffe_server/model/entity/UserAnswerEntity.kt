package org.example.financial_wunderwaffe_server.model.entity

import jakarta.persistence.*
import org.example.financial_wunderwaffe_server.model.view.UserAnswerView

@Entity
@Table(name = "User_answer")
data class UserAnswerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @ManyToOne
    @JoinColumn(name = "user_uid", nullable = false)
    val user: UserEntity,

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    val question: QuestionEntity,

    @ManyToOne
    @JoinColumn(name = "answer_id", nullable = false)
    val answer: AnswerEntity,

    @Column(nullable = false)
    val date: String
) {
    fun toUserAnswerView(): UserAnswerView =
        UserAnswerView(
            id = id,
            userUID = user.uid,
            questionID = question.id,
            answerID = answer.id,
            date = date
        )
}