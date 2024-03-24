package com.thevarungupta.blog.rest.api.controller

import com.thevarungupta.blog.rest.api.entity.Post
import com.thevarungupta.blog.rest.api.service.PostService
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
class PostController(
    val postService: PostService
) {

    @PostMapping
    fun createPost(@Valid @RequestBody post: Post): ResponseEntity<Post> {
        var data = postService.createPost(post)
        return ResponseEntity(data, HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllPosts(): ResponseEntity<List<Post>> {
        var data = postService.getAllPosts()
        return ResponseEntity(data, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getPostById(@PathVariable("id") postId: Long): ResponseEntity<Post> {
        var data = postService.getPostById(postId)
        return if (data == null)
            ResponseEntity(null, HttpStatus.NOT_FOUND)
        else
            ResponseEntity(data, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updatePost(
        @PathVariable("id") postId: Long,
        @Valid @RequestBody post: Post
    ): ResponseEntity<Post> {
        var data = postService.updatePost(postId, post)
        return ResponseEntity(data, HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/{id}")
    fun deletePost(@PathVariable("id") postId: Long): ResponseEntity<String> {
        postService.deletePost(postId)
        return ResponseEntity("post deleted successfully", HttpStatus.NO_CONTENT)
    }

}