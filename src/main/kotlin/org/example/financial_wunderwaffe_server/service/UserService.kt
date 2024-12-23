package org.example.financial_wunderwaffe_server.service

import org.example.financial_wunderwaffe_server.model.repository.UserRepository
import org.example.financial_wunderwaffe_server.model.view.UserView
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService (
    private val userRepository: UserRepository
) {

    fun findUserByUID(userUID: UUID): UUID? =
        userRepository.findByIdOrNull(userUID)?.uid

    fun createUser(userView: UserView): UserView {
        userView.password = getEncoder().encode(userView.password)
        return userRepository.save(userView.toUserEntity()).toUserView()
    }

    fun updateUser(userView: UserView): UserView =
        userRepository.save(userView.toUserEntity()).toUserView()

    private fun getEncoder(): PasswordEncoder =
        BCryptPasswordEncoder()

}