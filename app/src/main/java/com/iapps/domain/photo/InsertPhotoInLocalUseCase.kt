package com.iapps.domain.photo

import com.iapps.data.photo.local.PhotoEntity
import com.iapps.domain.base.BaseUseCase
import org.koin.core.component.KoinApiExtension


@OptIn(KoinApiExtension::class)
class InsertPhotoInLocalUseCase(private val photoRepository: PhotoRepository): BaseUseCase {
    suspend fun insertAll(photoItems: List<PhotoEntity>) {
         photoRepository.insertAll(photoItems)
    }
}