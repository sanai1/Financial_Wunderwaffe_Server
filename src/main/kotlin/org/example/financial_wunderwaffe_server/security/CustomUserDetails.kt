package org.example.financial_wunderwaffe_server.security

import org.example.financial_wunderwaffe_server.model.entity.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails (
    private val user: UserEntity
): UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> =
        user.authority.split(", ").map { SimpleGrantedAuthority(it) }.toList()

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.login

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}