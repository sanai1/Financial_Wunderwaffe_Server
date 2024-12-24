package org.example.financial_wunderwaffe_server.controller

import org.example.financial_wunderwaffe_server.model.view.BalanceView
import org.example.financial_wunderwaffe_server.model.view.UserView
import org.example.financial_wunderwaffe_server.service.UserService
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("api/v1/user")
class UserController (
    private val userService: UserService
) {

    @GetMapping("/login")
    fun findUserByUID(@RequestParam login: String): UUID? =
        userService.findUserByLogin(login)

    @PostMapping("/register")
    fun createUser(@RequestBody userView: UserView): Pair<UserView, List<BalanceView>> =
        userService.createUser(userView)

    @PutMapping("/update")
    fun updateUserByUID(@RequestBody userView: UserView): UserView =
        userService.updateUser(userView)

}