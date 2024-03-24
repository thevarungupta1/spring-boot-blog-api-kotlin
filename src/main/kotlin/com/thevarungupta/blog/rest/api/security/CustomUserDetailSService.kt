package com.thevarungupta.blog.rest.api.security

import com.thevarungupta.blog.rest.api.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class CustomUserDetailSService(
    val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(usernameOrEmail: String): UserDetails {
        var user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
            .orElseThrow{
                UsernameNotFoundException("user not found with username or email: $usernameOrEmail")
            }
        var authorities: Set<GrantedAuthority> = user
            .roles
            .stream()
            .map { role -> SimpleGrantedAuthority(role.name) }
            .collect(Collectors.toSet())
        return User(
            user.email,
            user.password,
            authorities
        )
    }
}