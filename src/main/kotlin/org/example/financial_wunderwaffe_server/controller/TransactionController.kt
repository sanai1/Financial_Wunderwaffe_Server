package org.example.financial_wunderwaffe_server.controller

import org.example.financial_wunderwaffe_server.model.request.TransactionView
import org.example.financial_wunderwaffe_server.service.TransactionService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/transaction")
class TransactionController (
    private val transactionsService: TransactionService
) {
    @GetMapping
    fun getTransactionsByUserUID(@RequestParam userUID: UUID) = transactionsService.findByUserUID(userUID)

    @PostMapping
    fun createTransaction(@RequestBody transactionView: TransactionView) = transactionsService.create(transactionView)

    @PutMapping
    fun updateTransactionByID(@RequestBody transactionView: TransactionView) = transactionsService.update(transactionView)

    @DeleteMapping
    fun deleteTransactionByID(@RequestParam transactionID: Long) = transactionsService.delete(transactionID)
}