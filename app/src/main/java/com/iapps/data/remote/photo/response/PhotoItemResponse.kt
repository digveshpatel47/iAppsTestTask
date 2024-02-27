package com.iapps.data.remote.photo.response

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("items") val items: List<PhotoItemResponse>
)

data class PhotoItemResponse(
    @SerializedName("title") val title: String,
    @SerializedName("media") val media: Media,
    @SerializedName("description") val description: String,
    @SerializedName("published") val published: String,
    @SerializedName("link") val link: String
)

data class Media(
    @SerializedName("m") val m: String
)