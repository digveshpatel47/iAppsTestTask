package com.iapps.data.photo.remote.photo.response

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("title") var title: String? = null,
    @SerializedName("link") var link: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("modified") var modified: String? = null,
    @SerializedName("generator") var generator: String? = null,
    @SerializedName("items") var items: ArrayList<PhotoItemResponse> = arrayListOf()
)

data class PhotoItemResponse(
    @SerializedName("title") var title: String? = null,
    @SerializedName("link") var link: String? = null,
    @SerializedName("media") var media: Media? = null,
    @SerializedName("date_taken") var dateTaken: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("published") var published: String? = null,
    @SerializedName("author") var author: String? = null,
    @SerializedName("author_id") var authorId: String? = null,
    @SerializedName("tags") var tags: String? = null
)

data class Media(
    @SerializedName("m") val m: String
)