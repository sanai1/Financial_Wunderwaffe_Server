package org.example.financial_wunderwaffe_server.service

import org.example.financial_wunderwaffe_server.model.request.UserView
import java.util.UUID

interface UserService {
    fun findByLogin(login: String): UUID
    fun create(userView: UserView): Boolean
    fun update(userView: UserView): Boolean
//    fun delete(userUID: UUID): Boolean
}