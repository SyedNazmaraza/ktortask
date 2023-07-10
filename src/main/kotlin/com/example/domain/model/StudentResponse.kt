package com.example.domain.model

import com.example.data.model.CreateStudentParams
import com.example.utils.ErrorCodes
import kotlinx.serialization.Serializable

@Serializable
data class StudentResponse(
    val student: CreateStudentParams,
    val errors: List<ResponseError> = emptyList()
)

@Serializable
data class ResponseError(
    val code : ErrorCodes , val message:String
)