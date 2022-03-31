package com.example.a36_retrofit.data

import com.example.a36_retrofit.data.model.PopularMovies
import com.example.a36_retrofit.data.network.MovieService

class MovieRepository {
    private val api = MovieService()
    suspend fun getPopularMovies(): PopularMovies = api.getPopularMovies()

}