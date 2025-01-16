package org.example.financial_wunderwaffe_server.service

import org.example.financial_wunderwaffe_server.model.request.UserAnswerView
import java.util.UUID

interface UserAnswerService {
    fun findByUserUID(userUID: UUID): List<UserAnswerView>
    fun create(listUserAnswerView: List<UserAnswerView>): List<Long>
    fun update(listUserAnswerView: List<UserAnswerView>): Boolean
}