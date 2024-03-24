package com.thevarungupta.blog.rest.api.repository

import com.thevarungupta.blog.rest.api.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {

    fun findByPostId(postId: Long): List<Comment>
}