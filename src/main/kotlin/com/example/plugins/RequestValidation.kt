package com.example.plugins

import com.example.data.model.CreateStudentParams
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configRequestValidation(){
    install(RequestValidation){
        validate<CreateStudentParams> {
            if(it.name == "" || it.name.length<5){
                ValidationResult.Invalid("Name Should Have Min 5 Characters")
            }
            else{
                ValidationResult.Valid
            }
        }
    }
}