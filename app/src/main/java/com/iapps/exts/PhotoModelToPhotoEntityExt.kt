package com.iapps.exts

import com.iapps.data.photo.local.PhotoEntity
import com.iapps.data.photo.remote.response.Media
import com.iapps.data.photo.remote.response.PhotoModel

fun PhotoModel.toPhotoEntity() = PhotoEntity (
    title = this.title,
    link = this.link,
    imageUrl = this.media?.m,
    description = description,
    published = published
)


fun List<PhotoModel>.photoModelListToPhotoEntityList(): List<PhotoEntity> {
    return this.map {
        it.toPhotoEntity()
    }
}


fun PhotoEntity.toPhotoModel() = PhotoModel  (
    title = this.title,
    link = this.link,
    media = Media(this.imageUrl),
    description = description,
    published = published
)


fun List<PhotoEntity>.photoEntityListToPhotoModelList(): List<PhotoModel> {
    return this.map {
        it.toPhotoModel()
    }
}
