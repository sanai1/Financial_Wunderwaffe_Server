package org.example.financial_wunderwaffe_server.service

import org.example.financial_wunderwaffe_server.model.request.TransactionView
import java.util.*

interface TransactionService {
    fun findByUserUID(userUID: UUID): List<TransactionView>
    fun create(transactionView: TransactionView): Long
    fun update(transactionView: TransactionView): Boolean
    fun delete(transactionID: Long): Boolean
}