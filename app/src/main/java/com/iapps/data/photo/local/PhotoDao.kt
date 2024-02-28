package com.iapps.data.photo.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photoItems: List<PhotoEntity>)

    @Query("SELECT * FROM photoentity ORDER BY published")
    fun getAllSortedByPublished(): Flow<MutableList<PhotoEntity>>

    @Query("delete from photoentity")
    suspend fun deleteAll()

}