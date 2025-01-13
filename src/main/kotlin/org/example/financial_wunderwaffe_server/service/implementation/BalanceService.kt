package org.example.financial_wunderwaffe_server.service.implementation

import org.example.financial_wunderwaffe_server.database.entity.BalanceEntity
import org.example.financial_wunderwaffe_server.database.entity.UserEntity
import org.example.financial_wunderwaffe_server.database.repository.BalanceRepository
import org.example.financial_wunderwaffe_server.database.repository.CurrencyRepository
import org.example.financial_wunderwaffe_server.database.repository.UserRepository
import org.example.financial_wunderwaffe_server.model.request.BalanceView
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BalanceService (
    private val balanceRepository: BalanceRepository,
    private val userRepository: UserRepository,
    private val currencyRepository: CurrencyRepository
){

    fun getBalanceByUserUID(userUID: UUID): List<BalanceView> {
        val user = userRepository.findByIdOrNull(userUID)
        return if (user != null)
            balanceRepository.getAllByUser(user).map { it.toBalanceView() }
        else emptyList()
    }

    fun createBalanceForNewUser(user: UserEntity): List<BalanceView> =
        currencyRepository.findAll().map {
            balanceRepository.save(
                BalanceEntity(
                    user = user,
                    currency = it,
                    amount = 0
                )
            ).toBalanceView()
        }

    fun updateBalanceBuID(balanceID: Long, newAmount: Long): BalanceView? {
        val balance = balanceRepository.findByIdOrNull(balanceID)
        return if (balance != null)
            balanceRepository.save(
                BalanceEntity(
                id = balanceID,
                user = balance.user,
                currency = balance.currency,
                amount = newAmount
            )
            ).toBalanceView()
        else null
    }

}