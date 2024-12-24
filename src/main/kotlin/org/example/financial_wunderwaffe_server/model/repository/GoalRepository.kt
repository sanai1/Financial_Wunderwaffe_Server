package org.example.financial_wunderwaffe_server.model.repository

import org.example.financial_wunderwaffe_server.model.entity.GoalEntity
import org.example.financial_wunderwaffe_server.model.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GoalRepository: JpaRepository<GoalEntity, Long> {
    fun findGoalByUser(user: UserEntity): Optional<GoalEntity>
}