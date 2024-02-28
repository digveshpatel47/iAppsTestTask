package com.iapps.data.photo.remote

import com.iapps.data.photo.remote.response.PhotoResponse
import retrofit2.http.GET

interface PhotoApiService {
    @GET("services/feeds/photos_public.gne?format=json&tags=cat&nojsoncallback=1")
    suspend fun fetchPhotoItems(): PhotoResponse
}