package com.example.data.schemas

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant


object StudentTable:Table("students") {
    val id = integer("id").autoIncrement()
    val name = varchar("name",256)
    val email = varchar("email",256)
    val pass = varchar("password",256)
    val branch = varchar("branch",256)
    val joiningDate = timestampWithTimeZone("joining_at")
    override val primaryKey: PrimaryKey = PrimaryKey(id)
}
fun Table.timestampWithTimeZone(name: String): Column<Instant> = registerColumn(name, JavaTimestampWithTimeZoneColumnType())
