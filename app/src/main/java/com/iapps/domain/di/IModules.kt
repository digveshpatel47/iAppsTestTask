package com.iapps.domain.di

import android.app.Application
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.iapps.data.photo.local.PhotoDao
import com.iapps.data.photo.local.PhotoDatabase
import com.iapps.data.photo.remote.photo.PhotoApiService
import com.iapps.data.PhotoDataRepository
import com.iapps.domain.photo.GetPhotoFromLocalUseCase
import com.iapps.domain.photo.PhotoRepository
import com.iapps.domain.photo.GetPhotoFromRemoteUseCase
import com.iapps.domain.photo.InsertPhotoInLocalUseCase
import com.iapps.ui.photo.PhotoViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private const val BASE_URL = "https://api.flickr.com/services/"
private const val I_APPS_DATABASE = "IAppsDatabase"

val databaseModule = module {
    single { providePhotoDatabase(androidApplication()) }
    single { providePhotoDao(get()) }
}

val netModules = module {
    single { provideInterceptors() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideGson() }
}

val apiModules = module {
    single { providePhotoApiService(get()) }
}

val repositoryModules = module {
    single { providePhotoRepository(get(),get()) }
}
val useCaseModules = module {
    single { providePhotoFromLocalUseCase(get()) }
    single { providePhotoFromRemoteUseCase(get()) }
    single { provideInsertPhotoUseCase(get()) }
}

@OptIn(KoinApiExtension::class)
val viewModelModules = module {
    viewModel {
        PhotoViewModel(get(),get(),get())
    }
}

private fun providePhotoRepository(photoApiService: PhotoApiService, photoDao: PhotoDao): PhotoRepository {
    return PhotoDataRepository(photoApiService,photoDao)
}
private fun providePhotoFromRemoteUseCase(photoRepository: PhotoRepository): GetPhotoFromRemoteUseCase {
    return GetPhotoFromRemoteUseCase(photoRepository)
}
private fun providePhotoFromLocalUseCase(photoRepository: PhotoRepository): GetPhotoFromLocalUseCase {
    return GetPhotoFromLocalUseCase(photoRepository)
}
private fun provideInsertPhotoUseCase(photoRepository: PhotoRepository): InsertPhotoInLocalUseCase {
    return InsertPhotoInLocalUseCase(photoRepository)
}


private fun providePhotoApiService(retrofit: Retrofit): PhotoApiService = retrofit.create(
    PhotoApiService::class.java)


private fun providePhotoDatabase(application: Application): PhotoDatabase {
    return Room.databaseBuilder(application, PhotoDatabase::class.java, I_APPS_DATABASE)
        .fallbackToDestructiveMigration()
        .build()
}

private fun providePhotoDao(database: PhotoDatabase): PhotoDao {
    return database.photoDao
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideOkHttpClient(interceptors: ArrayList<Interceptor>): OkHttpClient {
    val clientBuilder = OkHttpClient.Builder()
    clientBuilder.readTimeout(2, TimeUnit.MINUTES)
    clientBuilder.connectTimeout(2, TimeUnit.MINUTES)
    interceptors.forEach { clientBuilder.addInterceptor(it) }
    return clientBuilder.build()
}

private fun provideInterceptors(): ArrayList<Interceptor> {
    val interceptors = arrayListOf<Interceptor>()
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    interceptors.add(loggingInterceptor)
    return interceptors
}

fun provideGson(): Gson {
    return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
}
