package com.example.a36_retrofit.domain

import com.example.a36_retrofit.core.Respuesta
import com.example.a36_retrofit.data.MovieRepository
import com.example.a36_retrofit.data.model.*
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val repository:MovieRepository){

    suspend operator fun invoke(): Respuesta {
        return repository.getPopularMovies()
    }


}