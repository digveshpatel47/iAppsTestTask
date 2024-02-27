package com.iapps.data.local.photo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val imageUrl: String,
    val description: String,
    val published: String,
    val link: String
)