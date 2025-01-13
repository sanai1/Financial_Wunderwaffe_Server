package org.example.financial_wunderwaffe_server.database.entity

import jakarta.persistence.*
import org.example.financial_wunderwaffe_server.model.request.GoalView

@Entity
@Table(name = "Goal")
data class GoalEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @ManyToOne
    @JoinColumn(name = "user_uid", nullable = false)
    var user: UserEntity,

    @Column(nullable = false)
    val name: String,

    @Column(name = "create_date", nullable = false)
    val createData: String,

    @Column(name = "general_amount", nullable = false)
    val generalAmount: Long,

    @Column(name = "start_amount", nullable = false)
    val startAmount: Long,

    @Column(name = "delta_percentage", nullable = false)
    val deltaPercentage: Long,

    @Column(nullable = false)
    val description: String
) {
    fun toGoalView(): GoalView =
        GoalView(
            id = id,
            userUID = user.uid,
            name = name,
            createDate = createData,
            generalAmount = generalAmount,
            startAmount = startAmount,
            deltaPercentage = deltaPercentage,
            description = description
        )
}