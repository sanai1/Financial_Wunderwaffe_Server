package org.example.financial_wunderwaffe_server.service.implementation

import org.example.financial_wunderwaffe_server.database.repository.UserRepository
import org.example.financial_wunderwaffe_server.model.request.UserView
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService (
    private val userRepository: UserRepository,
    private val balanceService: BalanceService
) {

    fun findUserByLogin(login: String): UUID? =
        userRepository.findByLogin(login).map { it.uid }.orElseGet { null }

    fun createUser(userView: UserView): Boolean {
        userView.password = getEncoder().encode(userView.password)
        val user = userRepository.save(userView.toUserEntity())
        balanceService.createBalanceForNewUser(user)
        return true
    }

    fun updateUser(userView: UserView): UserView =
        userRepository.save(userView.toUserEntity()).toUserView()

    private fun getEncoder(): PasswordEncoder =
        BCryptPasswordEncoder()

}