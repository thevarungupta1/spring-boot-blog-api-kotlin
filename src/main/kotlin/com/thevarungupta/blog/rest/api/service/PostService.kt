package com.thevarungupta.blog.rest.api.service

import com.thevarungupta.blog.rest.api.entity.Post

interface PostService {
    fun createPost(newPost: Post): Post
    fun getAllPosts(): List<Post>
    fun getPostById(postId: Long): Post
    fun updatePost(postId: Long, updatePost: Post): Post
    fun deletePost(postId: Long)
}