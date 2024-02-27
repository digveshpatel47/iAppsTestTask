package com.iapps.data.photo.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PhotoEntity::class], version = 2, exportSchema = false)
abstract class PhotoDatabase : RoomDatabase() {
    abstract val photoDao: PhotoDao
}