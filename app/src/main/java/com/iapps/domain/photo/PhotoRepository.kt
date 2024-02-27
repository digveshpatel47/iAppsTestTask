package com.iapps.domain.photo

import com.iapps.data.photo.local.PhotoEntity
import com.iapps.data.photo.remote.base.Result
import com.iapps.data.photo.remote.photo.response.PhotoResponse
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    suspend fun fetchPhotoItems(): Flow<Result<PhotoResponse>>
    suspend fun insertAll(userEntity: List<PhotoEntity>)
    suspend fun getPhotoItemsSortedByPublished(): Flow<List<PhotoEntity>>
}