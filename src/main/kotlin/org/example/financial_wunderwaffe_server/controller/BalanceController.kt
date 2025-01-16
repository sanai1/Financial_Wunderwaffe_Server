//package org.example.financial_wunderwaffe_server.controller
//
//import org.example.financial_wunderwaffe_server.model.request.BalanceView
//import org.example.financial_wunderwaffe_server.service.implementation.BalanceService
//import org.springframework.web.bind.annotation.*
//import java.util.UUID
//
//@RestController
//@RequestMapping("/api/v1/balance")
//class BalanceController (
//    private val balanceService: BalanceService
//){
//
//    @GetMapping
//    fun getBalanceByUserUID(@RequestParam userUID: UUID): List<BalanceView> =
//        balanceService.getBalanceByUserUID(userUID)
//
//    @PutMapping
//    fun updateBalanceByID(
//        @RequestParam balanceID: Long,
//        @RequestParam newAmount: Long
//    ): BalanceView? =
//        balanceService.updateBalanceBuID(balanceID, newAmount)
//
//}