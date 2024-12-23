package org.example.financial_wunderwaffe_server.model.view

import org.example.financial_wunderwaffe_server.model.entity.UserEntity
import java.util.UUID

data class UserView(
    val uid: UUID,
    val login: String,
    var password: String
) {
    fun toUserEntity(): UserEntity =
        UserEntity(
            uid = uid,
            login = login,
            password = password
        )
}