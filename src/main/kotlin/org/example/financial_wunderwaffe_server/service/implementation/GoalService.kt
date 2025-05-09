//package org.example.financial_wunderwaffe_server.service.implementation
//
//import org.example.financial_wunderwaffe_server.database.repository.GoalRepository
//import org.example.financial_wunderwaffe_server.database.repository.UserRepository
//import org.example.financial_wunderwaffe_server.model.request.GoalView
//import org.springframework.data.repository.findByIdOrNull
//import org.springframework.stereotype.Service
//import java.util.Optional
//import java.util.UUID
//
//@Service
//class GoalService (
//    private val goalRepository: GoalRepository,
//    private val userRepository: UserRepository
//){
//
//    fun getGoalByUserUID(userUID: UUID): Optional<GoalView> =
//        goalRepository.findGoalByUser(
//            userRepository.findById(userUID).get()
//        ).map { it.toGoalView() }
//
//    fun createGoal(goalView: GoalView): GoalView? {
//        val user = userRepository.findByIdOrNull(goalView.userUID)
//        return if (user != null) {
//            if (goalRepository.findGoalByUser(user).get() == null)
//                goalRepository.save(goalView.toGoalEntity(user)).toGoalView()
//            else null
//        } else null
//    }
//
//    fun updateGoalByID(goalView: GoalView): GoalView? {
//        val user = userRepository.findByIdOrNull(goalView.userUID)
//        val goal = goalRepository.findByIdOrNull(goalView.id)
//        return if (user != null && goal != null)
//            goalRepository.save(goalView.toGoalEntity(goalView.id, user)).toGoalView()
//        else null
//    }
//
//    fun deleteGoalByID(goalID: Long): Boolean {
//        return if (goalRepository.findByIdOrNull(goalID) != null) {
//            goalRepository.deleteById(goalID)
//            true
//        } else false
//    }
//
//}