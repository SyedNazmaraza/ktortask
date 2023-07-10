package com.example.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginStudentParams (
    val email:String,
    val password:String
)