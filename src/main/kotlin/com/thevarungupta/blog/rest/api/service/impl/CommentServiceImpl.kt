package com.thevarungupta.blog.rest.api.service.impl

import com.thevarungupta.blog.rest.api.entity.Comment
import com.thevarungupta.blog.rest.api.entity.Post
import com.thevarungupta.blog.rest.api.exception.ResourceNotFoundException
import com.thevarungupta.blog.rest.api.payload.CommentDto
import com.thevarungupta.blog.rest.api.repository.CommentRepository
import com.thevarungupta.blog.rest.api.repository.PostRepository
import com.thevarungupta.blog.rest.api.service.CommentService
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    val commentRepository: CommentRepository,
    val postRepository: PostRepository
) : CommentService {

    override fun createComment(postId: Long, commentDto: CommentDto): CommentDto {
        var comment: Comment = mapToEntity(commentDto)

        // retrieve post by id
        val post = postRepository
            .findById(postId)
            .orElseThrow { ResourceNotFoundException("post", "postId", postId) }
        // set post to the comment entity
        comment.post = post
        commentRepository.save(comment)
        return mapToDTO(comment)
    }

    override fun getCommentByPostId(postId: Long): List<Comment> {
        // retrieve comment by postId
        return commentRepository.findByPostId(postId)
    }

    override fun getCommentById(postId: Long, commentId: Long): Comment {
        // retrieve post by post id
        val post: Post = postRepository
            .findById(postId)
            .orElseThrow { ResourceNotFoundException("post", "postId", postId) }
        // retrieve comment by comment id
        val comment: Comment = commentRepository
            .findById(commentId)
            .orElseThrow { ResourceNotFoundException("comment", "commentId", commentId) }
        // check post and comment belong to each other
        if (comment.post?.id != post.id)
            throw RuntimeException("comment does not belong to post")
        return comment;
    }

    override fun updateComment(postId: Long, commentId: Long, updateComment: Comment): Comment {
        // retrieve post by post id
        val post: Post = postRepository
            .findById(postId)
            .orElseThrow { ResourceNotFoundException("post", "postId", postId) }
        // retrieve comment by comment id
        val comment: Comment = commentRepository
            .findById(commentId)
            .orElseThrow { ResourceNotFoundException("comment", "commentId", commentId) }
        // check post and comment belong to each other
        if (comment.post?.id != post.id)
            throw RuntimeException("comment does not belong to post")

        comment.name = updateComment.name
        comment.email = updateComment.email
        comment.body = updateComment.body
        return commentRepository.save(comment)
    }

    override fun deleteComment(postId: Long, commentId: Long) {
        // retrieve post by post id
        val post: Post = postRepository
            .findById(postId)
            .orElseThrow { ResourceNotFoundException("post", "postId", postId) }
        // retrieve comment by comment id
        val comment: Comment = commentRepository
            .findById(commentId)
            .orElseThrow { ResourceNotFoundException("comment", "commentId", commentId) }
        // check post and comment belong to each other
        if (comment.post?.id != post.id)
            throw RuntimeException("comment does not belong to post")
        commentRepository.deleteById(commentId)
    }

    // convert entity to dto
    private fun mapToDTO(comment: Comment): CommentDto {
        return CommentDto(
            comment.id,
            comment.name,
            comment.email,
            comment.body
        )
    }

    // convert dto to entity
    private fun mapToEntity(commentDto: CommentDto): Comment {
        return Comment(
            commentDto.id,
            commentDto.name,
            commentDto.email,
            commentDto.body
        )
    }


}