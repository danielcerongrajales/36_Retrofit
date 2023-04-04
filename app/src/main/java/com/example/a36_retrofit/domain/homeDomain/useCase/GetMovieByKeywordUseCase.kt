package com.example.a36_retrofit.domain.homeDomain.useCase

import com.example.a36_retrofit.data.MovieRepositoryImpl
import javax.inject.Inject

class GetMovieByKeywordUseCase {
    @Inject lateinit var repository: MovieRepositoryImpl
//    suspend operator fun invoke(key:String): MovieByKeyword =repository.getMovieByKeyword(key)

}