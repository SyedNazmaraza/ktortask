package com.example.data.schemas

import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.IDateColumnType
import java.sql.ResultSet
import java.sql.Timestamp
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

class JavaTimestampWithTimeZoneColumnType : ColumnType(), IDateColumnType {
    override val hasTimePart: Boolean = true
    private val utcZoneOffset = ZoneOffset.UTC
    private val calendarTimeZoneInstance = Calendar.getInstance(TimeZone.getTimeZone(utcZoneOffset))
    private val dateTimeStringFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withLocale(Locale.ROOT).withZone(utcZoneOffset)

    override fun nonNullValueToString(value: Any): String {
        if (value !is Instant)
            error("$value is not a Instant!")

        return "'${dateTimeStringFormatter.format(value)}'"
    }

    override fun valueFromDB(value: Any): Instant {
        if (value is Timestamp) {
            return value.toInstant()
        } else if (value is Instant) {
            return value
        }

        error("$value is not a java.sql.Timestamp nor a java.time.Instant!")
    }

    override fun readObject(rs: ResultSet, index: Int): Any? {
        return rs.getTimestamp(index, calendarTimeZoneInstance)
    }

    override fun sqlType() = "timestamp with time zone"

    override fun notNullValueToDB(value: Any): Any {
        if (value !is Instant)
            error("$value is not a java.time.Instant!")

        return Timestamp.from(value)
    }
}