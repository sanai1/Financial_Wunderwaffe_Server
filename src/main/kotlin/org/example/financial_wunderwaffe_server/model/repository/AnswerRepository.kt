package org.example.financial_wunderwaffe_server.model.repository

import org.example.financial_wunderwaffe_server.model.entity.AnswerEntity
import org.example.financial_wunderwaffe_server.model.entity.QuestionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnswerRepository: JpaRepository<AnswerEntity, Long> {
    fun findAllByQuestion(questionEntity: QuestionEntity): List<AnswerEntity>
}