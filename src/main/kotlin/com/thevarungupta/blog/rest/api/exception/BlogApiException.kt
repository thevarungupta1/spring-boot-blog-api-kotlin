package com.thevarungupta.blog.rest.api.exception

import org.springframework.http.HttpStatus

class BlogApiException(
    var status: HttpStatus,
    var customMessage: String
) : RuntimeException(){


}