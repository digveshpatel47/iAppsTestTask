package com.iapps.domain.photo

import com.iapps.data.photo.local.PhotoEntity
import com.iapps.data.photo.remote.base.Result
import com.iapps.data.photo.remote.photo.response.PhotoResponse
import com.iapps.domain.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinApiExtension


@OptIn(KoinApiExtension::class)
class GetPhotoFromLocalUseCase(private val photoRepository: PhotoRepository): BaseUseCase {
    suspend fun getPhotoItemsSortedByPublished(): Flow<List<PhotoEntity>>  {
        return photoRepository.getPhotoItemsSortedByPublished()
    }
}