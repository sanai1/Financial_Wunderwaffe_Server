package org.example.financial_wunderwaffe_server.controller

import org.example.financial_wunderwaffe_server.model.request.UserView
import org.example.financial_wunderwaffe_server.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/user")
class UserController (
    private val userService: UserService
) {
    @GetMapping("/login")
    fun findUserByUID(@RequestParam login: String) = userService.findByLogin(login)

    @PostMapping("/register")
    fun createUser(@RequestBody userView: UserView) = userService.create(userView)

    @PutMapping("/update")
    fun updateUserByUID(@RequestBody userView: UserView) = userService.update(userView)
}