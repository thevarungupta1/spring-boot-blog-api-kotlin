package com.thevarungupta.blog.rest.api.service.impl

import com.thevarungupta.blog.rest.api.entity.Post
import com.thevarungupta.blog.rest.api.exception.ResourceNotFoundException
import com.thevarungupta.blog.rest.api.repository.PostRepository
import com.thevarungupta.blog.rest.api.service.PostService
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
    val postRepository: PostRepository
) : PostService {


    override fun createPost(newPost: Post): Post {
        return postRepository.save(newPost)
    }

    override fun getAllPosts(): List<Post> {
        return postRepository.findAll()
    }

    override fun getPostById(postId: Long): Post {
        val post = postRepository
            .findById(postId)
            .orElseThrow{
                ResourceNotFoundException("post", "postId", postId)
            }
        return post
    }

    override fun updatePost(postId: Long, updatePost: Post): Post {
        // get post by id from the database
        val post = postRepository
            .findById(postId)
            .orElseThrow{
                ResourceNotFoundException("post", "postId", postId)
            }
        // get the update data
        post.title = updatePost.title
        post.description = updatePost.description
        post.content = updatePost.content

        // save the changes
        return postRepository.save(post)
    }

    override fun deletePost(postId: Long) {
        postRepository
            .findById(postId)
            .orElseThrow{
                ResourceNotFoundException("post", "postId", postId)
            }
        postRepository.deleteById(postId)
    }
}