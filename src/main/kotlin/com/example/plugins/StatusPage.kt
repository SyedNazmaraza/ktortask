package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configStatusPage(){
    install(StatusPages){
        exception<Throwable>{call, cause ->
            call.respondText("Exception $cause")
        }
    }
}