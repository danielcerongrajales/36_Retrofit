package com.example.a36_retrofit.di

import com.example.a36_retrofit.BuildConfig
import com.example.a36_retrofit.data.model.PopularMovies
import com.example.a36_retrofit.data.network.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providePopularMovies(): PopularMovies {
        return PopularMovies()
    }

    @Singleton
    @Provides
    fun provideApiCLient(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(addClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(addClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }


    @Singleton
    @Provides
    fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain->
            val url: HttpUrl = chain.request().url().newBuilder()
                .addQueryParameter("api_key", BuildConfig.MOVIE_API_KEY)
                .build()
            val request=chain.request().newBuilder()
                .url(url)
                .build()
            chain.proceed(request)
        }
    }




//    @Singleton
//    @Provides
//    fun provideRetrofit(addClient: RetrofitHelper): Retrofit {
//        return addClient.getRetrofit()
//    }



//    @Singleton
//    @Provides
//    fun provideOkHttpClient(
//        authInterceptor: MovieAuthInterceptor
//    ): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(authInterceptor)
//            .build()
//    }



//    @Singleton
//    @Provides
//    fun provideAuthInterceptor(
//        authInterceptor: MovieAuthInterceptor
//    ): Interceptor {
//        return authInterceptor
//    }

}