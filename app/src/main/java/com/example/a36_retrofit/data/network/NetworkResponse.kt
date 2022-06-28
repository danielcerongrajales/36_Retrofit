package com.example.a36_retrofit.data.network

//sealed class RespuestaModel {
//    class Loading: RespuestaModel()
//    data class Success(val popularMovies: Any) : RespuestaModel()
//    data class Failure(val error : String) : RespuestaModel()
// data class NetworkException(val error : String) : RespuestaModel()
//    sealed class HttpErrors : RespuestaModel() {
//        data class ResourceForbidden(val exception: String) : HttpErrors()
//        data class ResourceNotFound(val exception: String) : HttpErrors()
//        data class InternalServerError(val exception: String) : HttpErrors()
//        data class BadGateWay(val exception: String) : HttpErrors()
//        data class ResourceRemoved(val exception: String) : HttpErrors()
//        data class RemovedResourceFound(val exception: String) : HttpErrors()
//    }
//}
//sealed class NetworkResponse< out T,out M> {
//
//    object Loading : NetworkResponse< Nothing,Nothing>()
//    /**
//     * Success response with body
//     */
//    data class Success<out T,out M>(val body: T,val message: M) : NetworkResponse<T,Nothing>()
//
//    /**
//     * Failure response with body
//     */
//    data class ApiError<out T,out M>(val body: T, val code: M) : NetworkResponse< T,M>()
//    /**
//     * Network error
//     */
//    data class NetworkError(val error: IOException) : NetworkResponse< Nothing, Nothing>()
//
//    /**
//     * For example, json parsing error
//     */
//    data class UnknownError(val error: Throwable) : NetworkResponse<Nothing, Nothing>()
//
//    object Finished: NetworkResponse<Nothing,Nothing>()
//}

sealed class NetworkResponse<out T> {
    object Loading : NetworkResponse<Nothing>()
    data class Success<out T>(val value: T): NetworkResponse<T>()
    data class GenericError(
        val code: Int? = null,
        val errorMessage: String? = null
    ): NetworkResponse<Nothing>()

    object NetworkError: NetworkResponse<Nothing>()
    object Finished: NetworkResponse<Nothing>()
}

