package com.iapps.data.remote.photo

import com.iapps.data.local.photo.PhotoDao
import com.iapps.domain.photo.PhotoRepository


class PhotoDataRepository(private val photoApiService: PhotoApiService, private val photoDao: PhotoDao):
    PhotoRepository {
}