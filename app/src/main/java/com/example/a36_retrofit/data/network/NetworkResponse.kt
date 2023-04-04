package com.example.a36_retrofit.data.network



sealed class NetworkResponse<out T> {
//    object Loading : NetworkResponse<Nothing>()
    data class Success<out T>(val value: T): NetworkResponse<T>()
    data class GenericError( val code: Int? = null, val errorMessage: String? = null ): NetworkResponse<Nothing>()

    object NetworkError: NetworkResponse<Nothing>()
//    object Finished: NetworkResponse<Nothing>()
}

