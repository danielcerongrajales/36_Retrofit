package com.example.a36_retrofit.di

import com.example.a36_retrofit.data.DetailsRepositoryImpl
import com.example.a36_retrofit.data.paging.ExampleRemoteMediator
import com.example.a36_retrofit.data.MovieRepositoryImpl
import com.example.a36_retrofit.data.database.MovieDatabase
import com.example.a36_retrofit.data.network.MovieService
import com.example.a36_retrofit.domain.detailsDomain.repository.DetailsRepository
import com.example.a36_retrofit.domain.homeDomain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMovieRepository(api : MovieService,
                               local: MovieDatabase,
                               exampleRemoteMediator: ExampleRemoteMediator
    ): MovieRepository {
        return MovieRepositoryImpl(api,local,exampleRemoteMediator)
    }

    @Provides
    @Singleton
    fun provideDetailsRepository(api : MovieService,
                               local: MovieDatabase,
                               exampleRemoteMediator: ExampleRemoteMediator
    ): DetailsRepository {
        return DetailsRepositoryImpl(api,local,exampleRemoteMediator)
    }
}