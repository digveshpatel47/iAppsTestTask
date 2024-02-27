package com.iapps.data.remote.photo

import com.iapps.common.HTTP_ERROR
import com.iapps.common.NO_INTERNET_CONNECTION
import com.iapps.common.SOMETHING_WENT_WRONG
import com.iapps.common.STATUS_CODE_SUCCESS
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
            photoApiService.getPhotos().apply {
                val data = body()?.data
                if (isSuccessful && data != null) {
                    if (body()?.errorCode == STATUS_CODE_SUCCESS) {
                        emit(Result.Success(data))
                    } else {
                        emit(Result.Error(body()?.errorMessage))
                    }
                } else {
                    emit(Result.Error(SOMETHING_WENT_WRONG))
                }
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