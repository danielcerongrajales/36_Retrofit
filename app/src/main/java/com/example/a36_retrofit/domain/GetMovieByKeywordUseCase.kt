package com.example.a36_retrofit.domain

import com.example.a36_retrofit.data.MovieRepository
import com.example.a36_retrofit.data.model.*
import javax.inject.Inject

class GetMovieByKeywordUseCase {
    @Inject lateinit var repository:MovieRepository
    suspend operator fun invoke(key:String): MovieByKeyword =repository.getMovieByKeyword(key)

}