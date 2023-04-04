package com.example.a36_retrofit.data.network

import com.example.a36_retrofit.data.network.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {
    @GET("movie/popular")
    suspend fun getMoviePopular(@Query("page") page: Int): Response<PopularMovies>
    @GET("movie/{id}/credits")
    suspend fun getCredits(@Path("id") id:Int): Response<Credits>
    @GET("movie/{id}")
    suspend fun getMovieDetailsById(@Path("id") id:Int): Response<MovieById>

    @GET("movie/top_rated")
    suspend fun getMovieTopRated(): Response<MovieTopRated>





    @GET("collection/{id}")
    suspend fun getCollectionMovieById(@Path("id") id:String): Response<CollectionById>
    @GET("keyword/{key}/movies")
    suspend fun getMovieByKeyword(@Path("key") key:String): Response<MovieByKeyword>
    @GET("movie/{id}/keywords")
    suspend fun getMovieKeywordsById(@Path("id") id:String): Response<KeywordsMovieById>
    @GET("search/keyword?")
    suspend fun getKeywordFromQuery(@Query( "query")query: String): Response<KeywordByQuery>
    @GET("search/movie")
    fun getSearchMovies(@Query("query") search: String): Response<MovieSearchResponse>
}