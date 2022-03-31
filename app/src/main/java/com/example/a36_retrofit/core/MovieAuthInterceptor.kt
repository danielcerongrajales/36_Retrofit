package com.example.a36_retrofit.core

import com.example.a36_retrofit.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class MovieAuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request().url().newBuilder()
            .addQueryParameter("api_key", BuildConfig.MOVIE_API_KEY)
            .build()
        val request=chain.request().newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}