package org.example.financial_wunderwaffe_server.service.implementation

import org.example.financial_wunderwaffe_server.database.repository.UserRepository
import org.example.financial_wunderwaffe_server.model.request.AssetView
import org.example.financial_wunderwaffe_server.model.request.CategoryView
import org.example.financial_wunderwaffe_server.model.request.UserView
import org.example.financial_wunderwaffe_server.service.AssetService
import org.example.financial_wunderwaffe_server.service.CategoryService
import org.example.financial_wunderwaffe_server.service.UserService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImplementation(
    private val userRepository: UserRepository,
    private val categoryService: CategoryService,
    private val assetService: AssetService,
) : UserService {

    override fun findByLogin(login: String): UUID =
        userRepository.findByLogin(login).map { it.uid }.orElseGet { null }

    override fun create(userView: UserView): Boolean {
        userView.password = getEncoder().encode(userView.password)
        val user = userRepository.save(userView.toUserEntity())
        categoryService.create(
            CategoryView(
                id = 0L,
                name = "Продажа актива",
                type = true,
                userUID = user.uid
            )
        )
        categoryService.create(
            CategoryView(
                id = 0L,
                name = "Покупка актива",
                type = false,
                userUID = user.uid
            )
        )
        assetService.create(
            AssetView(
                id = 0L,
                userUID = user.uid,
                title = "Фиат",
                amount = 0L
            )
        )
//        balanceService.createBalanceForNewUser(user)
        return true
    }

    override fun update(userView: UserView): Boolean {
        val user = userRepository.findByIdOrNull(userView.uid)
        return if (user != null) {
            userRepository.save(userView.toUserEntity())
            true
        } else false
    }

    private fun getEncoder(): PasswordEncoder =
        BCryptPasswordEncoder()

}