package com.iapps.data.remote.photo

import com.iapps.data.remote.photo.response.PhotoResponse
import retrofit2.http.GET

interface PhotoApiService {
    @GET("feeds/photos_public.gne?format=json&tags=cat&nojsoncallback=1")
    suspend fun fetchPhotoItems():PhotoResponse
}