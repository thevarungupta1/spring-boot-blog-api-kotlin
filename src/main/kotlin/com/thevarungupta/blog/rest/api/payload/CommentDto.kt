package com.thevarungupta.blog.rest.api.payload


data class CommentDto(
    var id: Long,
    var name: String,
    var email: String,
    var body: String
)
