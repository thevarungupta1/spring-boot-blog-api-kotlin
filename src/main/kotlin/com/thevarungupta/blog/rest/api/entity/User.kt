package com.thevarungupta.blog.rest.api.entity

import jakarta.persistence.*

@Entity
@Table(
    name = "users"
)
data class User(
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    var id: Long,
    var name: String,

    @Column(nullable = false, unique = true)
    var username: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var password: String,

    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = [CascadeType.ALL]
    )
    @JoinTable(
        name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: Set<Role>
)
