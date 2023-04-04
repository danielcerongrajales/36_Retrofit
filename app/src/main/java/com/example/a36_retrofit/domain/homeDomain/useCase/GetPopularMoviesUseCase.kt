package com.example.a36_retrofit.domain.homeDomain.useCase

import androidx.paging.PagingData
import com.example.a36_retrofit.domain.homeDomain.model.MovieItem
import com.example.a36_retrofit.domain.homeDomain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val repository: MovieRepository){

     operator fun invoke(): Flow<PagingData<MovieItem>> {
      val ala= repository.getMoviesPopularPager().flow

         return ala
//        return   when(val moviesNetwork= repository.getPopularMoviesFromApi(0)){
//
//            is DataState.Data -> {
//                if(moviesNetwork.data?.isNotEmpty() == true){
//                    repository.clearMovies()
//                   repository.insertMovies(moviesNetwork.data!!.map { it.toDatabase() })
//
//                  return  repository.getPopularMoviesFromLocal()
//                }else{
//                  return   repository.getPopularMoviesFromLocal()
//                }
//            }
//            is DataState.Error ->      {
//                return   repository.getPopularMoviesFromLocal()
//                }
//            is DataState.Loading ->{moviesNetwork}
//        }
//
////       return  when(movies){
//
////            is RespuestaItem.Success -> {
////                movies.popularMovies as List<MovieResulEntity>
////                movies
////                repository.insertMovies(movies.popularMovies.results)
////                RespuestaItem.Success(movies.popularMovies)
////            }
////           is RespuestaItem.Loading ->RespuestaItem.Loading()
////           is RespuestaItem.Failure -> TODO()
////           is RespuestaItem.HttpErrors.BadGateWay -> TODO()
////           is RespuestaItem.HttpErrors.InternalServerError -> TODO()
////           is RespuestaItem.HttpErrors.RemovedResourceFound -> TODO()
////           is RespuestaItem.HttpErrors.ResourceForbidden -> TODO()
////           is RespuestaItem.HttpErrors.ResourceNotFound -> TODO()
////           is RespuestaItem.HttpErrors.ResourceRemoved -> TODO()
////           is RespuestaItem.NetworkException -> TODO()
////           else -> {
////               RespuestaItem.Loading()
////       }
//
//
//
//
////       }

    }



}