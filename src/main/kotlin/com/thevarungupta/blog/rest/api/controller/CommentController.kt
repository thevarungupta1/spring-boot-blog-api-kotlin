package com.thevarungupta.blog.rest.api.controller

import com.thevarungupta.blog.rest.api.entity.Comment
import com.thevarungupta.blog.rest.api.payload.CommentDto
import com.thevarungupta.blog.rest.api.service.CommentService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/posts")
@RestController
class CommentController(
    val commentService: CommentService
) {

    @PostMapping("/{postId}/comments")
    fun createComment(
        @PathVariable("postId") postId: Long,
        @Valid @RequestBody comment: CommentDto
    ): ResponseEntity<CommentDto> {
        var data = commentService.createComment(postId, comment)
        return ResponseEntity(data, HttpStatus.CREATED)
    }

    @GetMapping("/{postId}/comments")
    fun getCommentsByPostId(@PathVariable("postId") postId: Long): ResponseEntity<List<Comment>> {
        var data = commentService.getCommentByPostId(postId)
        return ResponseEntity(data, HttpStatus.OK)
    }

    @GetMapping("/{postId}/comments/{id}")
    fun getCommentsByCommentId(
        @PathVariable("postId") postId: Long,
        @PathVariable("id") id: Long
    ): ResponseEntity<Comment> {
        var data = commentService.getCommentById(postId, id)
        return if (data == null)
            ResponseEntity(null, HttpStatus.NOT_FOUND)
        else
            ResponseEntity(data, HttpStatus.OK)
    }

    @PutMapping("/{postId}/comments/{id}")
    fun updateComment(
        @PathVariable("postId") postId: Long,
        @PathVariable("id") id: Long,
        @RequestBody comment: Comment
    ): ResponseEntity<Comment> {
        var data = commentService.updateComment(postId, id, comment)
        return ResponseEntity(data, HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/{postId}/comments/{id}")
    fun deleteComment(
        @PathVariable("postId") postId: Long,
        @PathVariable("id") id: Long
    ): ResponseEntity<String> {
        var data = commentService.deleteComment(postId, id)
        return ResponseEntity("comment deleted successfully", HttpStatus.NO_CONTENT)
    }


}