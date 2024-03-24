package com.thevarungupta.blog.rest.api.payload

import com.thevarungupta.blog.rest.api.entity.Post

data class PostResponse(
    val content: List<Post>
)
