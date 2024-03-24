package com.thevarungupta.blog.rest.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    var userDetailsService: UserDetailsService
) {
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain{
        http
            .csrf().disable()
            .authorizeHttpRequests{
                it.anyRequest()
                    .authenticated()
            }.httpBasic(Customizer.withDefaults())
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder{
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager? {
        return configuration.authenticationManager
    }

//    @Bean
//    fun userDetailsService(): UserDetailsService{
//        var mark = User.builder()
//            .username("mark")
//            //.password("123")
//            .password(passwordEncoder().encode("123"))
//            .roles("USER")
//            .build()
//
//        var admin = User.builder()
//            .username("admin")
//            .password(passwordEncoder().encode("123"))
//            .roles("ADMIN")
//            .build()
//        return InMemoryUserDetailsManager(mark, admin)
//    }

}