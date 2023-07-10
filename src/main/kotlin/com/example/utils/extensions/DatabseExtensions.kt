package com.example.utils.extensions

import com.example.data.model.CreateStudentParams
import com.example.data.schemas.StudentTable
import com.example.domain.model.StudentResponse
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toStudent(): CreateStudentParams =
    CreateStudentParams(
        name = this[StudentTable.name],
        email = this[StudentTable.email],
        password = this[StudentTable.pass],
        branch = this[StudentTable.branch]
    )
