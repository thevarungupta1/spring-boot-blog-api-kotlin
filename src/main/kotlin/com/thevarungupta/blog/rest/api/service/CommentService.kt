package com.thevarungupta.blog.rest.api.service

import com.thevarungupta.blog.rest.api.entity.Comment
import com.thevarungupta.blog.rest.api.payload.CommentDto

interface CommentService {
    fun createComment(postId: Long, commentDto: CommentDto): CommentDto
    fun getCommentByPostId(postId: Long): List<Comment>
    fun getCommentById(postId: Long, commentId: Long): Comment
    fun updateComment(postId: Long, commentId: Long, updateComment: Comment): Comment
    fun deleteComment(postId: Long, commentId: Long)
}