package com.iapps.data

import com.iapps.common.HTTP_ERROR
import com.iapps.common.NO_INTERNET_CONNECTION
import com.iapps.common.SOMETHING_WENT_WRONG
import com.iapps.data.photo.local.PhotoDao
import com.iapps.data.photo.local.PhotoEntity
import com.iapps.data.photo.remote.base.Result
import com.iapps.data.photo.remote.photo.PhotoApiService
import com.iapps.data.photo.remote.photo.response.PhotoResponse
import com.iapps.domain.photo.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class PhotoDataRepository(private val photoApiService: PhotoApiService, private val photoDao: PhotoDao):
    PhotoRepository {
    override suspend fun fetchPhotoItems() = flow {
        try {
            photoApiService.fetchPhotoItems().apply {
                emit(Result.Success(_data = this))
            }
        } catch (e: IOException) {
            // Handle IOException, no internet connection
            emit(Result.Error(NO_INTERNET_CONNECTION))
        } catch (e: HttpException) {
            // Handle HTTP exceptions
            emit(Result.Error("$HTTP_ERROR ${e.code()}"))
        } catch (e: Exception) {
            // Handle other exceptions
            emit(Result.Error(SOMETHING_WENT_WRONG))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun insertAll(userEntity: List<PhotoEntity>) {
        photoDao.insertAll(userEntity)
    }

    override suspend fun getPhotoItemsSortedByPublished(): Flow<List<PhotoEntity>> {
        TODO("Not yet implemented")
    }

    /*override suspend fun insertAll(userEntity: List<PhotoEntity>) {

    }

    override suspend fun getPhotoItemsSortedByPublished(): Flow<List<PhotoEntity>> {
       //
    }*/
}