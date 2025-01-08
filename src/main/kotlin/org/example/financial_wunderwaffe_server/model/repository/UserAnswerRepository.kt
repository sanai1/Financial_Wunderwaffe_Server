package org.example.financial_wunderwaffe_server.model.repository

import org.example.financial_wunderwaffe_server.model.entity.UserAnswerEntity
import org.example.financial_wunderwaffe_server.model.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserAnswerRepository: JpaRepository<UserAnswerEntity, Long> {
    fun findAllByUser(user: UserEntity): List<UserAnswerEntity>
}