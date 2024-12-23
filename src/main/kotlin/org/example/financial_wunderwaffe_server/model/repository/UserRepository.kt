package org.example.financial_wunderwaffe_server.model.repository

import org.example.financial_wunderwaffe_server.model.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<UserEntity, UUID> {
    fun findByLogin(login: String): Optional<UserEntity>
}