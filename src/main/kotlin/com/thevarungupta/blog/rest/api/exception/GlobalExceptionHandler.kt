package com.thevarungupta.blog.rest.api.exception

import com.thevarungupta.blog.rest.api.payload.ErrorDetail
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.Date
import java.util.HashMap

@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    // handle specific exception
    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(
        exception: ResourceNotFoundException,
        webRequest: WebRequest
    ): ResponseEntity<ErrorDetail> {
        var errorDetail = ErrorDetail(
            Date(),
            exception.message!!,
            webRequest.getDescription(false)
        )
        return ResponseEntity(errorDetail, HttpStatus.NOT_FOUND)
    }

    // blog api exception
    @ExceptionHandler(BlogApiException::class)
    fun handleBlogApiException(
        exception: BlogApiException,
        webRequest: WebRequest
    ): ResponseEntity<ErrorDetail> {
        var errorDetail = ErrorDetail(
            Date(),
            exception.customMessage,
            webRequest.getDescription(false)
        )
        return ResponseEntity(errorDetail, HttpStatus.BAD_REQUEST)
    }

    // global exception
    @ExceptionHandler(Exception::class)
    fun handleGlobalException(
        exception: Exception,
        webRequest: WebRequest
    ): ResponseEntity<ErrorDetail> {
        var errorDetail = ErrorDetail(
            Date(),
            exception.message!!,
            webRequest.getDescription(false)
        )
        return ResponseEntity(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.forEach {
            var fieldName = (it as FieldError).field
            var message = it.defaultMessage
            errors.put(fieldName, message)
        }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

}