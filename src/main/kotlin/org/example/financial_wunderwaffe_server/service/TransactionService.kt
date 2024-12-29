package org.example.financial_wunderwaffe_server.service

import org.example.financial_wunderwaffe_server.model.repository.CategoryRepository
import org.example.financial_wunderwaffe_server.model.repository.TransactionRepository
import org.example.financial_wunderwaffe_server.model.repository.UserRepository
import org.example.financial_wunderwaffe_server.model.view.TransactionView
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TransactionService (
    private val transactionRepository: TransactionRepository,
    private val userRepository: UserRepository,
    private val categoryRepository: CategoryRepository
) {

    fun getTransactionsByUserUID(userUID: UUID): List<TransactionView> =
        transactionRepository.findTransactionsByUser(
            userRepository.findById(userUID).get()
        ).map { it.toTransactionView() }

    fun createTransaction(transactionView: TransactionView): Long {
        val user = userRepository.findByIdOrNull(transactionView.userUID)
        val category = categoryRepository.findByIdOrNull(transactionView.categoryID)
        return if (user != null && category != null)
            transactionRepository.save(transactionView.toTransactionEntity(user, category)).id
        else 0
    }

    fun updateTransaction(transactionView: TransactionView): Boolean {
        val user = userRepository.findByIdOrNull(transactionView.userUID)
        val category = categoryRepository.findByIdOrNull(transactionView.categoryID)
        val transaction = transactionRepository.findByIdOrNull(transactionView.id)
        return if (user != null && category != null && transaction != null) {
            transactionRepository.save(
                transactionView.toTransactionEntity(transactionView.id, user, category))
            true
        } else false
    }

    fun deleteTransaction(transactionID: Long): Boolean {
        return if (transactionRepository.findByIdOrNull(transactionID) != null) {
            transactionRepository.deleteById(transactionID)
            true
        } else false
    }

}