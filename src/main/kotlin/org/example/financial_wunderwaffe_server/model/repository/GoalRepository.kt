package org.example.financial_wunderwaffe_server.model.repository

import org.example.financial_wunderwaffe_server.model.entity.GoalEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GoalRepository: JpaRepository<GoalEntity, Long>