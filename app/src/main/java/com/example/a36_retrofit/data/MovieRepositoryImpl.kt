package com.example.a36_retrofit.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.a36_retrofit.data.database.CacheResponseHandler
import com.example.a36_retrofit.data.database.MovieDatabase
import com.example.a36_retrofit.data.database.entities.MovieEntity
import com.example.a36_retrofit.data.network.model.*
import com.example.a36_retrofit.data.network.ApiResponseHandler
import com.example.a36_retrofit.data.network.MovieService
import com.example.a36_retrofit.data.paging.ExampleRemoteMediator
import com.example.a36_retrofit.data.utils.*
import com.example.a36_retrofit.domain.homeDomain.model.MovieItem
import com.example.a36_retrofit.domain.homeDomain.model.toDomain
import com.example.a36_retrofit.domain.homeDomain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api : MovieService,
    private val local: MovieDatabase,
    private val exampleRemoteMediator: ExampleRemoteMediator
): MovieRepository {

      override suspend fun getMoviesPopularFromApi(nextPage: Int): DataState<List<MovieItem>> {
        val networkResult =safeApiCall(Dispatchers.IO)  {api.getPopularMovies(1)}
          val response = object: ApiResponseHandler<List<MovieItem>,Response<PopularMovies>>(
            response = networkResult,
//            stateEvent = null
        ){
            override suspend fun handleSuccess(resultObj: Response<PopularMovies>): DataState<List<MovieItem>> {
                return DataState.Data(
                    response = StateMessage(Response(
                        message = "get from network",
                        messageType = MessageType.Success
                    )),
//                    data= resultObj.body().results.
                    data =  resultObj.body()?.results!!.map {
                        it.toDomain()
                    },
//                    stateEvent = null
                )
            }


        }.getResult()

        return response!!

    }

      override suspend fun getMoviesPopularFromLocal(search: String): DataState<List<MovieItem>> {
        TODO("Not yet implemented")
    }

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

    @OptIn(ExperimentalPagingApi::class)
    override fun getMoviesPopularPager() : Pager<Int, MovieItem> {
        val dbSource = {
            local.getMovieDao().pagingSource()
        }
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator =   exampleRemoteMediator,
            pagingSourceFactory = dbSource
        )

    }
      override suspend fun insertMovies(list:List<MovieEntity>){
//        =local.getMovieDao().insertAll(list)
    }
      override suspend fun clearMovies() =local.getMovieDao().deleteAllMovies()

//    suspend fun getKeywordByQuery(query:String): KeywordByQuery = api.getKeywordFromQuery(query)
//    suspend fun getMovieByKeyword(key:String): MovieByKeyword = api.getMovieByKeyword(key)
//    suspend fun getMovieById(id:Int): MovieById = api.getMovieById(id)

      override suspend fun clearDetailMovie() =local.getMovieDetailsDao().deleteAllMovies()



    override suspend fun getMovieTopRatedFromApi(): DataState<List<MovieItem>> {
        val networkResult =safeApiCall(Dispatchers.IO)  { api.getMovieTopRated()}
        val response = object: ApiResponseHandler<List<MovieItem>,Response<MovieTopRated>>(
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



            override suspend fun handleSuccess(resultObj: Response<MovieTopRated>): DataState<List<MovieItem>>? {
               return DataState.Data(
                    response = StateMessage(Response(
                        message = "get from network",
                        messageType = MessageType.Success
                    )),
//                    data= resultObj.body().results.
                    data = resultObj.body()?.results?.map {
                        it.toDomain()
                    },
//                    stateEvent = null
                )
            }


        }.getResult()

        return response!!
    }
//    suspend fun getCollectionById(id:String): CollectionById = api.getCollectionMovieById(id)
//suspend fun getKeywordsMovieById(id:String): KeywordsMovieById = api.getMovieKeywordsById(id)


}



