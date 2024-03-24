package com.thevarungupta.blog.rest.api.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import java.util.HashMap
import java.util.HashSet

@Entity
@Table(
    name = "posts",
    uniqueConstraints = arrayOf(
        UniqueConstraint(
            columnNames = ["title"]
        )
    )
)
data class Post(
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    var id: Long,

    @NotEmpty(message = "post title cannot be null or empty")
    @Size(min = 2, message = "post title should be at least 2 character")
    @Column(name = "title", nullable = false)
    var title: String,

    @NotEmpty(message = "post description cannot be null or empty")
    @Size(min = 10, message = "post description should be at least 10 character")
    @Column(name = "description", nullable = false)
    var description: String,

    @NotEmpty(message = "post content cannot be null or empty")
    @Size(min = 2, message = "post content should be at least 2 character")
    @Column(name = "content", nullable = false)
    var content: String,

    @OneToMany(
        mappedBy = "post",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var comments: MutableSet<Comment> = HashSet()
)
