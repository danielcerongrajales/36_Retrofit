package com.example.a36_retrofit.core

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit =Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(addClient())
        .build()

    private fun addClient(): OkHttpClient =OkHttpClient.Builder()
        .addInterceptor(MovieAuthInterceptor()).build()
}