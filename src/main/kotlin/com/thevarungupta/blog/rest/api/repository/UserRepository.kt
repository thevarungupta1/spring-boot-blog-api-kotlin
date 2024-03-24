package com.thevarungupta.blog.rest.api.repository

import com.thevarungupta.blog.rest.api.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long>{

    fun findByEmail(email: String): Optional<User>
    fun findByUsername(username: String): Optional<User>
    fun findByUsernameOrEmail(username: String, email: String): Optional<User>
}