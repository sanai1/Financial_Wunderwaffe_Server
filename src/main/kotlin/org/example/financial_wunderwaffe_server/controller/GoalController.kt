//package org.example.financial_wunderwaffe_server.controller
//
//import org.example.financial_wunderwaffe_server.model.request.GoalView
//import org.example.financial_wunderwaffe_server.service.implementation.GoalService
//import org.springframework.web.bind.annotation.DeleteMapping
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.PostMapping
//import org.springframework.web.bind.annotation.PutMapping
//import org.springframework.web.bind.annotation.RequestBody
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RequestParam
//import org.springframework.web.bind.annotation.RestController
//import java.util.Optional
//import java.util.UUID
//
//@RestController
//@RequestMapping("/api/v1/goal")
//class GoalController (
//    private val goalService: GoalService
//){
//
//    @GetMapping
//    fun getGoalByUserUID(@RequestParam userUID: UUID): Optional<GoalView> =
//        goalService.getGoalByUserUID(userUID)
//
//    @PostMapping
//    fun createGoal(@RequestBody goalView: GoalView): GoalView? =
//        goalService.createGoal(goalView)
//
//    @PutMapping
//    fun updateGoalByID(@RequestBody goalView: GoalView): GoalView? =
//        goalService.updateGoalByID(goalView)
//
//    @DeleteMapping
//    fun deleteGoalByID(@RequestParam goalID: Long): Boolean =
//        goalService.deleteGoalByID(goalID)
//
//}