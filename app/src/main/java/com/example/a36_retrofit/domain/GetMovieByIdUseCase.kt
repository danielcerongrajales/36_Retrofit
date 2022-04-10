package com.example.a36_retrofit.domain

import com.example.a36_retrofit.data.MovieRepository
import com.example.a36_retrofit.data.model.*

class GetMovieByIdUseCase {
    private val repository=MovieRepository()

    suspend operator fun invoke(id:String): MovieById =repository.getMovieById(id)

}