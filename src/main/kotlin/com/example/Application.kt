package com.example

import com.example.data.DataFactory
import com.example.plugins.configureContentNegotiation
import com.example.plugins.configureKoin
import com.example.plugins.configureRoutes
import io.ktor.server.application.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    DataFactory.init()
    configureContentNegotiation()
    configureKoin()
    configureRoutes()

}
