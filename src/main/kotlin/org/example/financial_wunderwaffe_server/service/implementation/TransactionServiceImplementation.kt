package org.example.financial_wunderwaffe_server.service.implementation

import org.example.financial_wunderwaffe_server.database.repository.CategoryRepository
import org.example.financial_wunderwaffe_server.database.repository.TransactionRepository
import org.example.financial_wunderwaffe_server.database.repository.UserRepository
import org.example.financial_wunderwaffe_server.model.request.TransactionView
import org.example.financial_wunderwaffe_server.service.TransactionService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TransactionServiceImplementation (
    private val transactionRepository: TransactionRepository,
    private val userRepository: UserRepository,
    private val categoryRepository: CategoryRepository
): TransactionService {

    override fun findByUserUID(userUID: UUID): List<TransactionView> =
        transactionRepository.findTransactionsByUser(
            userRepository.findById(userUID).get()
        ).map { it.toTransactionView() }

    override fun create(transactionView: TransactionView): Long {
        val user = userRepository.findByIdOrNull(transactionView.userUID)
        val category = categoryRepository.findByIdOrNull(transactionView.categoryID)
        return if (user != null && category != null)
            transactionRepository.save(transactionView.toTransactionEntity(user, category)).id
        else 0
    }

    override fun update(transactionView: TransactionView): Boolean {
        val user = userRepository.findByIdOrNull(transactionView.userUID)
        val category = categoryRepository.findByIdOrNull(transactionView.categoryID)
        val transaction = transactionRepository.findByIdOrNull(transactionView.id)
        return if (user != null && category != null && transaction != null) {
            transactionRepository.save(
                transactionView.toTransactionEntity(transactionView.id, user, category))
            true
        } else false
    }

    override fun delete(transactionID: Long): Boolean {
        return if (transactionRepository.findByIdOrNull(transactionID) != null) {
            transactionRepository.deleteById(transactionID)
            true
        } else false
    }

}