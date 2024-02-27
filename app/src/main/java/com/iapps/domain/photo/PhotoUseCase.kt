package com.iapps.domain.photo

import com.iapps.data.remote.base.Result
import com.iapps.data.remote.photo.response.PhotoResponse
import com.iapps.domain.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinApiExtension


@OptIn(KoinApiExtension::class)
class PhotoUseCase(private val photoRepository: PhotoRepository): BaseUseCase {
    suspend fun fetchPhotoItems(): Flow<Result<PhotoResponse>> {
        return photoRepository.fetchPhotoItems()
    }
}