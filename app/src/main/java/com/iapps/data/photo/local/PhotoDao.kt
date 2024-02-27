package com.iapps.data.photo.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy


@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(photoItems: List<PhotoEntity>)


    /*@Query("SELECT * FROM photoentity ORDER BY published DESC")
    fun getAllSortedByPublished(): Flow<List<PhotoEntity>>*/

}