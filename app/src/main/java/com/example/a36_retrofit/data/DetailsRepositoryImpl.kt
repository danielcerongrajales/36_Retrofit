package com.example.a36_retrofit.data

import com.example.a36_retrofit.data.database.CacheResponseHandler
import com.example.a36_retrofit.data.database.MovieDatabase
import com.example.a36_retrofit.data.database.entities.MovieDetailsEntity
import com.example.a36_retrofit.data.database.entities.MovieEntity
import com.example.a36_retrofit.data.network.model.*
import com.example.a36_retrofit.data.network.ApiResponseHandler
import com.example.a36_retrofit.data.network.MovieService
import com.example.a36_retrofit.data.paging.ExampleRemoteMediator
import com.example.a36_retrofit.data.utils.*
import com.example.a36_retrofit.domain.detailsDomain.model.CreditsItem
import com.example.a36_retrofit.domain.detailsDomain.model.MovieDetailsItem
import com.example.a36_retrofit.domain.detailsDomain.model.toDomain
import com.example.a36_retrofit.domain.homeDomain.model.MovieItem
import com.example.a36_retrofit.domain.homeDomain.model.toDomain
import com.example.a36_retrofit.domain.detailsDomain.repository.DetailsRepository
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val api : MovieService,
    private val local: MovieDatabase,
    private val exampleRemoteMediator: ExampleRemoteMediator
): DetailsRepository {




    suspend fun getPopularMoviesFromLocal(): DataState<List<MovieItem>> {

        val cacheResult = safeCacheCall(Dispatchers.IO){
            local.getMovieDao().getAllMovies()
        }

        val response = object: CacheResponseHandler<List<MovieItem>, List<MovieEntity>>(
            response = cacheResult
//            stateEvent = null
        ){

            override suspend fun handleSuccess(resultObj: List<MovieEntity>): DataState<List<MovieItem>> {
                return DataState.Data(
                    response = StateMessage(Response(
                        message = "get from local",
                        messageType = MessageType.Success
                    )),
                    data = resultObj.map{it.toDomain()}
//                    stateEvent = null
                )
            }

        }.getResult()

        return response!!


    }



//    suspend fun getKeywordByQuery(query:String): KeywordByQuery = api.getKeywordFromQuery(query)
//    suspend fun getMovieByKeyword(key:String): MovieByKeyword = api.getMovieByKeyword(key)
//    suspend fun getMovieById(id:Int): MovieById = api.getMovieById(id)

      override suspend fun clearDetailMovie() =local.getMovieDetailsDao().deleteAllMovies()
      override suspend fun insertMovieDetails(list:MovieDetailsEntity) =local.getMovieDetailsDao().insertMovie(list)
      override suspend fun getMovieDetailsFromApi(nextPage: Int): DataState<MovieDetailsItem> {
        val networkResult =safeApiCall(Dispatchers.IO)  { api.getMovieById(nextPage)}
        val response = object: ApiResponseHandler<MovieDetailsItem,Response<MovieById>>(
            response = networkResult,
//            stateEvent = null
        ){
//            override suspend fun handleSuccess(resultObj: Response<PopularMovies>): DataState<List<Movie>> {
//                return DataState.Data(
//                    response = StateMessage(Response(
//                        message = "get from network",
//                        messageType = MessageType.Success
//                    )),
////                    data= resultObj.body().results.
//                    data =  resultObj.body()?.results!!.map {
//                        it.toDomain()
//                    },
////                    stateEvent = null
//                )
//            }

            override suspend fun handleSuccess(resultObj: Response<MovieById>): DataState<MovieDetailsItem>? {
                return DataState.Data(
                    response = StateMessage(Response(
                        message = "get from network",
                        messageType = MessageType.Success
                    )),
//                    data= resultObj.body().results.
                    data = resultObj.body()?.toDomain(),
//                    stateEvent = null
                )
            }


        }.getResult()

        return response!!

    }
      override suspend fun getMovieDetailsFromLocal(nextPage: Int): DataState<MovieDetailsItem> {

        val cacheResult = safeCacheCall(Dispatchers.IO){
            local.getMovieDetailsDao().getDetailsMovie(nextPage)
        }

        val response = object: CacheResponseHandler<MovieDetailsItem, MovieDetailsEntity>(
            response = cacheResult
//            stateEvent = null
        ){

//            override suspend fun handleSuccess(resultObj: List<MovieResultEntity>): DataState<MovieDetails> {
//
//            }

            override suspend fun handleSuccess(resultObj: MovieDetailsEntity): DataState<MovieDetailsItem>? {
               return DataState.Data(
                    response = StateMessage(Response(
                        message = "get from local",
                        messageType = MessageType.Success
                    )),
                    data = resultObj.toDomain()
//                    stateEvent = null
                )
            }

        }.getResult()

        return response!!


    }

      override suspend fun getMovieCreditsApi(movieId: Int): DataState<CreditsItem> {
        val networkResult =safeApiCall(Dispatchers.IO)  { api.getCredits(movieId)}
        val response = object: ApiResponseHandler<CreditsItem,Response<Credits>>(
            response = networkResult,
//            stateEvent = null
        ){
//            override suspend fun handleSuccess(resultObj: Response<PopularMovies>): DataState<List<Movie>> {
//                return DataState.Data(
//                    response = StateMessage(Response(
//                        message = "get from network",
//                        messageType = MessageType.Success
//                    )),
////                    data= resultObj.body().results.
//                    data =  resultObj.body()?.results!!.map {
//                        it.toDomain()
//                    },
////                    stateEvent = null
//                )
//            }

            override suspend fun handleSuccess(resultObj: Response<Credits>): DataState<CreditsItem>? {
                return DataState.Data(
                    response = StateMessage(Response(
                        message = "get from network",
                        messageType = MessageType.Success
                    )),
//                    data= resultObj.body().results.
                    data = resultObj.body()?.toDomain(),
//                    stateEvent = null
                )
            }


        }.getResult()

        return response!!

    }
//    suspend fun getCollectionById(id:String): CollectionById = api.getCollectionMovieById(id)
//suspend fun getKeywordsMovieById(id:String): KeywordsMovieById = api.getMovieKeywordsById(id)


}



