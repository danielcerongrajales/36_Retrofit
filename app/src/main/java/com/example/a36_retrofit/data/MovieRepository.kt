package com.example.a36_retrofit.data

import com.example.a36_retrofit.core.Respuesta
import com.example.a36_retrofit.data.model.*
import com.example.a36_retrofit.data.network.MovieService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val api : MovieService){

    suspend fun getPopularMovies(): Respuesta = api.getPopularMovies()
    suspend fun getKeywordByQuery(query:String): KeywordByQuery = api.getKeywordFromQuery(query)
    suspend fun getMovieByKeyword(key:String): MovieByKeyword = api.getMovieByKeyword(key)
    suspend fun getMovieById(id:Int): MovieById = api.getMovieById(id)
    suspend fun getCollectionById(id:String): CollectionById = api.getCollectionMovieById(id)
suspend fun getKeywordsMovieById(id:String): KeywordsMovieById = api.getMovieKeywordsById(id)


}