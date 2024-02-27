package com.iapps.data.remote.base


data class ApiResponse<out T>(
    val errorCode: String? = null,
    val data: T? = null,
    val errorMessage: String? = null,
)


