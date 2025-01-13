package org.example.financial_wunderwaffe_server.model.request

import org.example.financial_wunderwaffe_server.database.entity.UserEntity
import org.springframework.security.core.GrantedAuthority
import java.util.UUID

data class UserView(
    val uid: UUID,
    val login: String,
    var password: String,
    val authority: String
) {
    fun toUserEntity(): UserEntity =
        UserEntity(
            uid = uid,
            login = login,
            password = password,
            authority = authority
        )
}