package com.thevarungupta.blog.rest.api.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException(
    var resourceName: String,
    var fieldName: String,
    var fieldValue: Long
) : RuntimeException(
    "$resourceName not found with $fieldName: '$fieldValue'"
) {

}