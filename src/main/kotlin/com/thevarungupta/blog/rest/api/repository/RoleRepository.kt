package com.thevarungupta.blog.rest.api.repository

import com.thevarungupta.blog.rest.api.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<Role, Long> {
}