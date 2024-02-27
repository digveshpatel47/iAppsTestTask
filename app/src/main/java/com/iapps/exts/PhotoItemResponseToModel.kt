package com.iapps.exts

import com.iapps.data.photo.local.PhotoEntity
import com.iapps.data.photo.remote.photo.response.PhotoItemResponse
import com.iapps.models.PhotoModel


fun PhotoItemResponse.toPhotoEntity(): PhotoEntity {
    return PhotoEntity(
        title = this.title,
        imageUrl = this.media?.m,
        description = this.description,
        published = this.published,
        link = this.link
    )
}


fun PhotoEntity.toPhotoModel(): PhotoModel {
    return PhotoModel(
        description = this.description,
        imageUrl = this.imageUrl,
        link = this.link,
        published = this.published
    )
}