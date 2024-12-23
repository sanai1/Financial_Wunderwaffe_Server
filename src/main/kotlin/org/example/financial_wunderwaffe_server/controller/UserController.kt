package org.example.financial_wunderwaffe_server.controller

import org.example.financial_wunderwaffe_server.model.view.UserView
import org.example.financial_wunderwaffe_server.service.UserService
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("api/v1/user")
class UserController (
    private val userService: UserService
) {

    @GetMapping
    fun findUserByUID(@RequestParam userUID: UUID): UUID? =
        userService.findUserByUID(userUID)

    @PostMapping
    fun createUser(@RequestBody userView: UserView): UserView =
        userService.createUser(userView)

    @PutMapping("/update")
    fun updateUserByUID(@RequestBody userView: UserView): UserView =
        userService.updateUser(userView)

}