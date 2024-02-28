package com.iapps.data.photo.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "link")
    val link: String?=null,
    @ColumnInfo(name = "title")
    val title: String?=null,
    @ColumnInfo(name = "description")
    val description: String?=null,
    @ColumnInfo(name = "published")
    val published: String?=null,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String?=null
)


