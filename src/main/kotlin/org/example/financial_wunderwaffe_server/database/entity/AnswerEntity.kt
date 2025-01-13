package org.example.financial_wunderwaffe_server.database.entity

import jakarta.persistence.*
import org.example.financial_wunderwaffe_server.model.request.AnswerView

@Entity
@Table(name = "Answer")
data class AnswerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(nullable = false)
    var text: String,

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    val question: QuestionEntity
) {
    fun toAnswerView(): AnswerView =
        AnswerView(
            id = id,
            text = text,
            questionID = question.id
        )
}