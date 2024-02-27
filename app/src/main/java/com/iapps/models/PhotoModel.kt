package com.iapps.models

data class PhotoModel(
    var description: String? = null,
    var imageUrl: String? = null,
    var link: String? = null,
    val published: String?=null
)