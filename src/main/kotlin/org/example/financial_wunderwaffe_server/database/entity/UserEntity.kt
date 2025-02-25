package org.example.financial_wunderwaffe_server.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.example.financial_wunderwaffe_server.model.request.UserView
import java.util.UUID

@Entity
@Table(name = "User")
data class UserEntity(
    @Id
    val uid: UUID,

    @Column(nullable = false, unique = true)
    val login: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val authority: String
) {
    fun toUserView(): UserView =
        UserView(
            uid = uid,
            login = login,
            password = password,
            authority = authority
        )
}