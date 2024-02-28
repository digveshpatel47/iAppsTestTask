package com.iapps.data.photo.local

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


class DateConverters {
    @TypeConverter
    fun toLocalDateTime(value: String?): LocalDateTime? {
        return value?.let {
            val instant = Instant.parse(it)
            LocalDateTime.ofInstant(instant, ZoneId.of("UTC"))
        }
    }

    @TypeConverter
    fun fromLocalDateTime(date: LocalDateTime?): String? {
        return date?.atZone(ZoneId.of("UTC"))?.toInstant()?.toString()
    }
}