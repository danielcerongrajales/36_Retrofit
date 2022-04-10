package com.example.a36_retrofit.data

import com.example.a36_retrofit.data.model.*
import com.example.a36_retrofit.data.network.MovieService

class MovieRepository {
    private val api = MovieService()
    suspend fun getPopularMovies(): PopularMovies = api.getPopularMovies()
    suspend fun getKeywordByQuery(query:String): KeywordByQuery = api.getKeywordFromQuery(query)
    suspend fun getMovieByKeyword(key:String): MovieByKeyword = api.getMovieByKeyword(key)
    suspend fun getMovieById(id:String): MovieById = api.getMovieById(id)
    suspend fun getCollectionById(id:String): CollectionById = api.getCollectionMovieById(id)
suspend fun getKeywordsMovieById(id:String): KeywordsMovieById = api.getMovieKeywordsById(id)


}