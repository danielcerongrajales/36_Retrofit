package com.example.a36_retrofit.data.network

import android.util.Log
import com.example.a36_retrofit.core.RetrofitHelper
import com.example.a36_retrofit.data.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.create

class MovieService {
    private val retrofit=RetrofitHelper.getRetrofit()
    private var pop:PopularMovies =  PopularMovies()
    suspend fun getPopularMovies(): PopularMovies = withContext(Dispatchers.IO) {

         retrofit.create(ApiClient::class.java).getMoviePopular().body()?:pop
    }


      suspend fun getKeywordFromQuery(query:String): KeywordByQuery = withContext(Dispatchers.IO) {

          retrofit.create(ApiClient::class.java).getKeywordFromQuery(query).body()!!
     }


    suspend fun getMovieByKeyword(key:String): MovieByKeyword = withContext(Dispatchers.IO) {

        retrofit.create(ApiClient::class.java).getMovieByKeyword(key).body()!!
    }


    suspend fun getMovieById(id:String): MovieById = withContext(Dispatchers.IO) {

    retrofit.create(ApiClient::class.java).getMovieById(id).body()!!
}

    suspend fun getCollectionMovieById(id:String): CollectionById = withContext(Dispatchers.IO) {

        retrofit.create(ApiClient::class.java).getCollectionMovieById(id).body()!!
    }

    suspend fun getMovieKeywordsById(id:String): KeywordsMovieById = withContext(Dispatchers.IO) {

        retrofit.create(ApiClient::class.java).getMovieKeywordsById(id).body()!!
    }
}