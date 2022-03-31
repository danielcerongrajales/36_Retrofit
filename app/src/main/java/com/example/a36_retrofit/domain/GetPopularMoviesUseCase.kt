package com.example.a36_retrofit.domain

import com.example.a36_retrofit.data.MovieRepository
import com.example.a36_retrofit.data.model.PopularMovies

class GetPopularMoviesUseCase {
    private val repository=MovieRepository()
    suspend operator fun invoke():PopularMovies=repository.getPopularMovies()
}