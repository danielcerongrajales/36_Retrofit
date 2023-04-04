package com.example.a36_retrofit.domain.homeDomain.useCase

import com.example.a36_retrofit.data.utils.DataState
import com.example.a36_retrofit.domain.homeDomain.model.MovieItem
import com.example.a36_retrofit.domain.homeDomain.repository.MovieRepository
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(): DataState<List<MovieItem>> {
return repository.getMovieTopRatedFromApi()
//        when (val a = repository.getMovieTopRatedFromApi()) {
//            is DataState.Data -> {
//                moviesFinal = a.data!!
//                state = a.response!!
//            }
//            is DataState.Error -> {
//                moviesFinal = a.data!!
//                state = a.response!!
//            }
//        }
    }

}