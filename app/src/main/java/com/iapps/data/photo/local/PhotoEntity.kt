package com.iapps.data.photo.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    val title: String?=null,
    val imageUrl: String?=null,
    val description: String?=null,
    val published: String?=null,
    val link: String?=null
)