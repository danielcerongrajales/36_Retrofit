package com.example.a36_retrofit.data.network

import android.util.Log
import com.example.a36_retrofit.core.RetrofitHelper
import com.example.a36_retrofit.data.model.PopularMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.create

class MovieService {
    private val retrofit=RetrofitHelper.getRetrofit()
    private var pop:PopularMovies =  PopularMovies()

      suspend fun getPopularMovies(): PopularMovies  = withContext(Dispatchers.IO) {

         retrofit.create(ApiClient::class.java).getMoviePopular().body()?:pop
     }
}