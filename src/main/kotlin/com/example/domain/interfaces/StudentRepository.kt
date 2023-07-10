package com.example.domain.interfaces

import com.example.data.model.CreateStudentParams
import com.example.utils.BaseResponse

interface StudentRepository {
    suspend fun register(params: CreateStudentParams): BaseResponse<CreateStudentParams>
}