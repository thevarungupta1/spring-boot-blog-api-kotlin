package com.thevarungupta.blog.rest.api.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

@Entity
@Table(
    name = "comments"
)
data class Comment(
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    var id: Long,

    @NotEmpty(message = "name cannot be null or empty")
    var name: String,

    @NotEmpty(message = "email cannot be null or empty")
    @Email
    var email: String,

    @NotEmpty(message = "body cannot be null or empty")
    @Size(min = 10, message = "comment body should be at least 10 character ")
    var body: String,

    @ManyToOne(
        fetch = FetchType.LAZY
    )
    @JoinColumn(
        name = "post_id",
        nullable = false
    )
    var post: Post? = null
)
