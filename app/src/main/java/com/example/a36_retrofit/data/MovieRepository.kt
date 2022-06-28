package com.example.a36_retrofit.data

import com.example.a36_retrofit.data.database.CacheResponseHandler
import com.example.a36_retrofit.data.database.dao.MovieDao
import com.example.a36_retrofit.data.database.entities.MovieResultEntity
import com.example.a36_retrofit.data.model.*
import com.example.a36_retrofit.data.network.ApiResponseHandler
import com.example.a36_retrofit.data.network.MovieService
import com.example.a36_retrofit.data.utils.*
import com.example.a36_retrofit.domain.model.Movie
import com.example.a36_retrofit.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api : MovieService,
    private val local:MovieDao){

    suspend fun getPopularMoviesFromApi(): DataState<List<Movie>> {
        val networkResult =safeApiCall(Dispatchers.IO)  {api.getPopularMovies()}
        val response = object: ApiResponseHandler< List<Movie>,Response<PopularMovies>>(
            response = networkResult,
            stateEvent = null
        ){
            override suspend fun handleSuccess(resultObj: Response<PopularMovies>): DataState<List<Movie>> {
                return DataState.Data(
                    response = StateMessage(Response(
                        message = "get from network",
                        messageType = MessageType.Success
                    )),
                    data =  resultObj.body()?.results!!.map {
                        it.toDomain()
                    },
                    stateEvent = null
                )
            }


        }.getResult()

        return response!!

    }
    suspend fun getPopularMoviesFromLocal(): DataState<List<Movie>> {

        val cacheResult = safeCacheCall(Dispatchers.IO){
            local.getAllMovies()
        }

        val response = object: CacheResponseHandler<List<Movie>, List<MovieResultEntity>>(
            response = cacheResult,
            stateEvent = null
        ){

            override suspend fun handleSuccess(resultObj: List<MovieResultEntity>): DataState<List<Movie>> {
                return DataState.Data(
                    response = StateMessage(Response(
                        message = "get from local",
                        messageType = MessageType.Success
                    )),
                    data = resultObj.map{it.toDomain()},
                    stateEvent = null
                )
            }

        }.getResult()

        return response!!


    }

    suspend fun insertMovies(list:List<MovieResultEntity>) =local.insertAll(list)
    suspend fun clearMovies() =local.deleteAllMovies()

//    suspend fun getKeywordByQuery(query:String): KeywordByQuery = api.getKeywordFromQuery(query)
    suspend fun getMovieByKeyword(key:String): MovieByKeyword = api.getMovieByKeyword(key)
    suspend fun getMovieById(id:Int): MovieById = api.getMovieById(id)



//    suspend fun getCollectionById(id:String): CollectionById = api.getCollectionMovieById(id)
//suspend fun getKeywordsMovieById(id:String): KeywordsMovieById = api.getMovieKeywordsById(id)


}



