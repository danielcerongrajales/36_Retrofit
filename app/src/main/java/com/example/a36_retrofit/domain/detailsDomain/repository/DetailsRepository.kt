package com.example.a36_retrofit.domain.detailsDomain.repository

import com.example.a36_retrofit.data.database.entities.MovieDetailsEntity
import com.example.a36_retrofit.data.utils.DataState
import com.example.a36_retrofit.domain.detailsDomain.model.CreditsItem
import com.example.a36_retrofit.domain.detailsDomain.model.MovieDetailsItem

interface DetailsRepository {

    //credits movie domain
    suspend fun getMovieCreditsApi(movieId: Int): DataState<CreditsItem>




    //detail movie domain
    suspend fun getMovieDetailsFromApi(nextPage: Int): DataState<MovieDetailsItem>
    //detail movies room
    suspend fun getMovieDetailsFromLocal(nextPage: Int): DataState<MovieDetailsItem>
    suspend fun insertMovieDetails(list: MovieDetailsEntity)




    suspend fun clearDetailMovie()


}