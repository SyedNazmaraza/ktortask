package com.example.data.repository

import com.example.data.DataFactory.dbQuery
import com.example.data.schemas.StudentTable
import com.example.utils.extensions.toStudent
import com.example.data.model.CreateStudentParams
import com.example.domain.interfaces.StudentRepository
import com.example.utils.BaseResponse
import com.example.utils.ErrorCodes
import org.jetbrains.exposed.sql.*
import java.sql.Timestamp
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.spi.TimeZoneNameProvider

class StudentRepositoryImpl() : StudentRepository {
    override suspend fun register(params: CreateStudentParams): BaseResponse<CreateStudentParams> {
        return try {
            val currentTime = LocalDateTime.now()
            val zoneId = ZoneId.of("America/New_York")
            val formattedDateTime = currentTime.atZone(zoneId).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            dbQuery{
                StudentTable.insert {
                    it[name] = params.name
                    it[email] = params.email
                    it[pass] = params.password
                    it[branch] = params.branch
                    it[joiningDate] = Instant.now()
                }.resultedValues?.singleOrNull()?.let {
                    BaseResponse.SuccessResponse<CreateStudentParams>(data = it.toStudent())
                }?:BaseResponse.ErrorResponse(errorCodes = ErrorCodes.DATABASE_ERROR)
            }
        }
        catch (e:Exception){
            BaseResponse.ErrorResponse(errorCodes = ErrorCodes.DATABASE_ERROR)
        }

    }
}