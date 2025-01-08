package org.example.financial_wunderwaffe_server.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.example.financial_wunderwaffe_server.model.view.QuestionView

@Entity
@Table(name = "Question")
data class QuestionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(nullable = false)
    var text: String,

    @Column(nullable = false)
    var isEnabled: Boolean
)  {
    fun toQuestionView(): QuestionView =
        QuestionView(
            id = id,
            text = text,
            isEnabled = isEnabled
        )
}
