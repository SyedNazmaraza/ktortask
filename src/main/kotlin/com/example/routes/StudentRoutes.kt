package com.example.routes

import com.example.data.model.CreateStudentParams
import com.example.domain.usecases.RegisterStudent
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.studentRoutes(service: RegisterStudent){
    route("/student"){
        post ("/register"){
            var params = call.receive<CreateStudentParams>()
            call.respond(service(params))
        }
    }
}