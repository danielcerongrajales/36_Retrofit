package com.example.a36_retrofit.domain.detailsDomain.useCase

import com.example.a36_retrofit.data.database.entities.toDatabase
import com.example.a36_retrofit.data.utils.DataState
import com.example.a36_retrofit.domain.detailsDomain.model.CreditsItem
import com.example.a36_retrofit.domain.detailsDomain.model.MovieDetailsItem
import com.example.a36_retrofit.domain.detailsDomain.repository.DetailsRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(private val repository: DetailsRepository) {
    lateinit var moviesNetwork: DataState<MovieDetailsItem>
    lateinit var movieCast: DataState<CreditsItem>
    suspend operator fun invoke(id: Int) {


        withContext(IO) {
            val result1 = async {
                moviesNetwork = movieDetails(id)
            }

            val result2 = async {
                movieCast = movieCast(id)
            }

            result1.await()
            result2.await()


        }


    }


    private suspend fun movieDetails( id: Int): DataState<MovieDetailsItem> {
          when (val moviesNetwork = repository.getMovieDetailsFromApi(id)) {

            is DataState.Data -> {
                return  if (moviesNetwork.data != null) {
                    repository.clearDetailMovie()
                    repository.insertMovieDetails(moviesNetwork.data.toDatabase())

                      repository.getMovieDetailsFromLocal(id)
                } else {
                      repository.getMovieDetailsFromLocal(id)
                }
            }
            is DataState.Error -> {
                return  repository.getMovieDetailsFromLocal(id)
            }
            //            is DataState.Loading ->{moviesNetwork}
        }
    }

    private suspend fun movieCast(
        id: Int
    ): DataState<CreditsItem> {
         when (val moviesNetwork = repository.getMovieCreditsApi(id)) {

            is DataState.Data -> {
                return if (moviesNetwork.data != null) {
            //                    repository.clearDetailMovie()
            //                    repository.insertMovieDetails(moviesNetwork.data.toDatabase())

                    repository.getMovieCreditsApi(id)
                } else {
                    repository.getMovieCreditsApi(id)
                }
            }
            is DataState.Error -> {
                return repository.getMovieCreditsApi(id)
            }
            //            is DataState.Loading ->{moviesNetwork}
        }
    }

//
//    data class response(val a: DataState<MovieDetailsItem>, val b: DataState<CreditsItem>)
}