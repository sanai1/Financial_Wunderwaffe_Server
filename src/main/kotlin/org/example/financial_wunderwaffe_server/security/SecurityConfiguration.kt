package org.example.financial_wunderwaffe_server.security

import org.example.financial_wunderwaffe_server.database.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfiguration (
    private val userRepository: UserRepository
) {

    @Bean
    fun userDetailsService(): CustomUserDetailsService = CustomUserDetailsService(userRepository)

    @Bean
    fun providerAuthentication(): AuthenticationProvider {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(userDetailsService())
        authenticationProvider.setPasswordEncoder(getEncoder())
        return authenticationProvider
    }

    @Bean
    fun config(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.csrf {csrf -> csrf.disable() }

        httpSecurity.authorizeHttpRequests { httpRequest -> httpRequest
            .requestMatchers("api/v1/user/register").permitAll()
            .requestMatchers("api/v1/admin/**").hasAuthority("ADMIN")
            .requestMatchers("api/v1/server/**").hasAuthority("SERVER")
            .requestMatchers("api/v1/**").authenticated()
            .anyRequest().denyAll()
        }

        httpSecurity.httpBasic(Customizer.withDefaults())

        return httpSecurity.build()
    }

    @Bean
    fun getEncoder(): PasswordEncoder =
        BCryptPasswordEncoder()

}