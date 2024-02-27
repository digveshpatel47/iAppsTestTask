package com.iapps.data.remote.photo

import com.iapps.data.remote.base.ApiResponse
import com.iapps.data.remote.photo.response.PhotoItemResponse
import com.iapps.data.remote.photo.response.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface PhotoApiService {
    /*@POST("login")
    suspend fun getPhotos(): Response<ApiResponse<PhotoItemResponse>>*/

    @GET("services/feeds/photos_public.gne?format=json&tags=cat&nojsoncallback=1")
    suspend fun getPhotos(): PhotoResponse
}