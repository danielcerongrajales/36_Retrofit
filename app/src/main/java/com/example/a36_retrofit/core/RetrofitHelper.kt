package com.example.a36_retrofit.core

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitHelper (){
//    fun getRetrofit(): Retrofit =Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .client(addClient())
//        .build()
//
//    private fun addClient(): OkHttpClient =OkHttpClient.Builder()
//        .addInterceptor(MovieAuthInterceptor()).build()
}

//class RetrofitHelper @Inject constructor(private val addClient:OkHttpClient){
//    fun getRetrofit(): Retrofit =Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .client(addClient)
//        .build()
//
//}