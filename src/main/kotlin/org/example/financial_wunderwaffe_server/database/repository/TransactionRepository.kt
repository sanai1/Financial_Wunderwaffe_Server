package org.example.financial_wunderwaffe_server.database.repository

import org.example.financial_wunderwaffe_server.database.entity.TransactionEntity
import org.example.financial_wunderwaffe_server.database.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository: JpaRepository<TransactionEntity, Long> {
    fun findTransactionsByUser(user: UserEntity): List<TransactionEntity>
}