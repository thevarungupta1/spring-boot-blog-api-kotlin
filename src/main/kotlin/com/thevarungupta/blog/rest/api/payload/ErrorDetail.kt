package com.thevarungupta.blog.rest.api.payload

import java.util.Date

data class ErrorDetail(
    var timestamp: Date,
    var message: String,
    var details: String
)
