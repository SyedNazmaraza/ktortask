package com.example.plugins

import com.example.domain.usecases.RegisterStudent
import com.example.routes.studentRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRoutes(){
    routing {
        val service by inject<RegisterStudent>()
        studentRoutes(service)
    }
}