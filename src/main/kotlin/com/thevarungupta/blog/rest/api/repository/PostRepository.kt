package com.thevarungupta.blog.rest.api.repository

import com.thevarungupta.blog.rest.api.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>{
}