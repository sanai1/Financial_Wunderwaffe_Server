package org.example.financial_wunderwaffe_server.database.repository

import org.example.financial_wunderwaffe_server.database.entity.UserAnswerEntity
import org.example.financial_wunderwaffe_server.database.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserAnswerRepository: JpaRepository<UserAnswerEntity, Long> {
    fun findAllByUser(user: UserEntity): List<UserAnswerEntity>
}