package com.example.a36_retrofit.domain

import com.example.a36_retrofit.data.MovieRepository
import com.example.a36_retrofit.data.model.*
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor (private val repository:MovieRepository) {


    suspend operator fun invoke(id:Int): MovieById =repository.getMovieById(id)

}