package org.example.financial_wunderwaffe_server.security

import org.example.financial_wunderwaffe_server.database.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomUserDetailsService (
    private val userRepository: UserRepository,
): UserDetailsService {

    override fun loadUserByUsername(username: String): CustomUserDetails {
        val user = userRepository.findByLogin(username)

        return user.map { CustomUserDetails(it) }.orElseThrow() {RuntimeException("User not found: $username")}
    }

}