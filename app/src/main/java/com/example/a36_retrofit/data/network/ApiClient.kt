package com.example.a36_retrofit.data.network

import com.example.a36_retrofit.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {
    @GET("/3/movie/popular")
    suspend fun getMoviePopular(): Response<PopularMovies>

    @GET("/3/search/keyword?")
    suspend fun getKeywordFromQuery(@Query( "query")query: String): Response<KeywordByQuery>

    @GET("/3/keyword/{key}/movies")
    suspend fun getMovieByKeyword(@Path("key") key:String): Response<MovieByKeyword>

    @GET("/3/movie/{id}")
    suspend fun getMovieById(@Path("id") id:Int): Response<MovieById>

    @GET("/3/collection/{id}")
    suspend fun getCollectionMovieById(@Path("id") id:String): Response<CollectionById>

    @GET("/3/movie/{id}/keywords")
    suspend fun getMovieKeywordsById(@Path("id") id:String): Response<KeywordsMovieById>

}