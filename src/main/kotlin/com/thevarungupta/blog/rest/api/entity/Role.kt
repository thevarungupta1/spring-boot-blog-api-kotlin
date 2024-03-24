package com.thevarungupta.blog.rest.api.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(
    name = "roles"
)
data class Role(
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    var id: Long,
    var name: String
)
