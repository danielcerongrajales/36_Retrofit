package com.example.a36_retrofit.domain

import com.example.a36_retrofit.data.MovieRepository
import com.example.a36_retrofit.data.database.entities.toDatabase
import com.example.a36_retrofit.domain.model.Movie
import com.example.a36_retrofit.data.utils.DataState
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val repository:MovieRepository){

    suspend operator fun invoke(): DataState<List<Movie>> {
        return   when(val moviesNetwork= repository.getPopularMoviesFromApi()){

            is DataState.Data -> {
                if(moviesNetwork.data?.isNotEmpty() == true){
                    repository.clearMovies()
                   repository.insertMovies(moviesNetwork.data.map { it.toDatabase() })

                  return  moviesNetwork
                }else{
                  return   repository.getPopularMoviesFromLocal()
                }
            }
            is DataState.Error ->      {
                return   repository.getPopularMoviesFromLocal()
                }
            is DataState.Loading ->{moviesNetwork}
        }

//       return  when(movies){

//            is RespuestaItem.Success -> {
//                movies.popularMovies as List<MovieResulEntity>
//                movies
//                repository.insertMovies(movies.popularMovies.results)
//                RespuestaItem.Success(movies.popularMovies)
//            }
//           is RespuestaItem.Loading ->RespuestaItem.Loading()
//           is RespuestaItem.Failure -> TODO()
//           is RespuestaItem.HttpErrors.BadGateWay -> TODO()
//           is RespuestaItem.HttpErrors.InternalServerError -> TODO()
//           is RespuestaItem.HttpErrors.RemovedResourceFound -> TODO()
//           is RespuestaItem.HttpErrors.ResourceForbidden -> TODO()
//           is RespuestaItem.HttpErrors.ResourceNotFound -> TODO()
//           is RespuestaItem.HttpErrors.ResourceRemoved -> TODO()
//           is RespuestaItem.NetworkException -> TODO()
//           else -> {
//               RespuestaItem.Loading()
//       }




//       }

    }


}