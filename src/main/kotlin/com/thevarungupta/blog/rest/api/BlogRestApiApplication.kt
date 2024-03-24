package com.thevarungupta.blog.rest.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.sql.DriverManager.println

@SpringBootApplication
class BlogRestApiApplication

fun main(args: Array<String>) {
	runApplication<BlogRestApiApplication>(*args)
}
