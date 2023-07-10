package com.example.domain.usecases

import com.auth0.jwt.JWT
import com.example.data.model.CreateStudentParams
import com.example.domain.interfaces.StudentRepository
import com.example.domain.model.ResponseError
import com.example.domain.model.StudentResponse
import com.example.utils.BaseResponse
import com.example.utils.ErrorCodes

class RegisterStudent(
    private val repo : StudentRepository,
    private val jwtToken: CreateJwtToken
) {
    suspend operator fun invoke(params: CreateStudentParams):StudentResponse{

        return when(val student = repo.register(params)){
            is BaseResponse.SuccessResponse -> jwtToken(student.data).run {  StudentResponse(student.data)}
            is BaseResponse.ErrorResponse -> StudentResponse(student = params, errors = listOf(ResponseError(student.errorCodes,student.errorCodes.message)))

        }


    }
}