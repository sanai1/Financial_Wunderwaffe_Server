package org.example.financial_wunderwaffe_server.model.view

import org.example.financial_wunderwaffe_server.model.entity.GoalEntity
import org.example.financial_wunderwaffe_server.model.entity.UserEntity
import java.util.UUID

data class GoalView(
    val id: Long,
    val userUID: UUID,
    val name: String,
    val createDate: String,
    val generalAmount: Long,
    val startAmount: Long,
    val deltaPercentage: Long,
    val description: String
) {
    fun toGoalEntity(user: UserEntity): GoalEntity =
        GoalEntity(
            id = id,
            user = user,
            name = name,
            createData = createDate,
            generalAmount = generalAmount,
            startAmount = startAmount,
            deltaPercentage = deltaPercentage,
            description = description
        )
}
