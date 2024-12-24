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

    fun createTransaction(transactionView: TransactionView): TransactionView? {
        val user = userRepository.findByIdOrNull(transactionView.userUID)
        val category = categoryRepository.findByIdOrNull(transactionView.categoryID)
        return if (user != null && category != null)
            transactionRepository.save(transactionView.toTransactionEntity(user, category)).toTransactionView()
        else null
    }

    fun updateTransaction(transactionView: TransactionView): TransactionView? {
        val user = userRepository.findByIdOrNull(transactionView.userUID)
        val category = categoryRepository.findByIdOrNull(transactionView.categoryID)
        val transaction = transactionRepository.findByIdOrNull(transactionView.id)
        return if (user != null && category != null && transaction != null)
            transactionRepository.save(
                transactionView.toTransactionEntity(transactionView.id, user, category)
            ).toTransactionView()
        else null
    }

    // TODO: удаление невозможно проверить не совершив GET
    fun deleteTransaction(transactionID: Long) =
        transactionRepository.deleteById(transactionID)

}