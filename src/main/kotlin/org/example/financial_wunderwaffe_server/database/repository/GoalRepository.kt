package org.example.financial_wunderwaffe_server.database.repository

import org.example.financial_wunderwaffe_server.database.entity.GoalEntity
import org.example.financial_wunderwaffe_server.database.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GoalRepository: JpaRepository<GoalEntity, Long> {
    fun findGoalByUser(user: UserEntity): Optional<GoalEntity>
}