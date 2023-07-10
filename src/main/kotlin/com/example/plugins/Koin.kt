package com.example.plugins

import com.example.domain.interfaces.StudentRepository
import com.example.data.repository.StudentRepositoryImpl
import com.example.domain.usecases.CreateJwtToken
import com.example.domain.usecases.RegisterStudent
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.koin

fun Application.configureKoin(){
    val module = module {
        single<StudentRepository>{ StudentRepositoryImpl() }
        single { CreateJwtToken() }
        single { RegisterStudent(get(),get()) }
    }
    koin {
        modules(module)
    }
}