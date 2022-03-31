package com.example.a36_retrofit.data.network

import com.example.a36_retrofit.data.model.PopularMovies
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {
    @GET("/3/movie/popular")
    suspend fun getMoviePopular(): Response<PopularMovies>
}