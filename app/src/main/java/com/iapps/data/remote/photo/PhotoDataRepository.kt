package com.iapps.data.remote.photo

import com.iapps.common.HTTP_ERROR
import com.iapps.common.NO_INTERNET_CONNECTION
import com.iapps.common.UNKNOWN_ERROR
import com.iapps.data.local.photo.PhotoDao
import com.iapps.data.remote.base.Result
import com.iapps.domain.photo.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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
            emit(Result.Error("$UNKNOWN_ERROR ${e.message}"))
        }
    }.flowOn(Dispatchers.IO)

    /*override suspend fun insertUser(userEntity: UserEntity) {
        withContext(Dispatchers.IO) {
            photoDao.insertUser(userEntity)
        }
    }*/
}