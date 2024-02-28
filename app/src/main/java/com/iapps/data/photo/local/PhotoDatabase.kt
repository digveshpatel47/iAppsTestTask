package com.iapps.data.photo.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PhotoEntity::class], version = 2, exportSchema = false)
@TypeConverters(DateConverters::class)
abstract class PhotoDatabase : RoomDatabase() {
    abstract val photoDao: PhotoDao
}