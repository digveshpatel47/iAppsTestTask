package com.iapps.domain.photo

import com.iapps.data.remote.base.Result
import com.iapps.data.remote.photo.response.PhotoResponse
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {

    suspend fun fetchPhotoItems():Flow<Result<PhotoResponse>>

   // suspend fun getPhotoItemsSortedByPublished(): List<PhotoItem>

    //suspend fun doLogin(loginRequest: LoginRequest): Flow<Result<UserEntity>>
    //suspend fun insertUser(userEntity: UserEntity)
}