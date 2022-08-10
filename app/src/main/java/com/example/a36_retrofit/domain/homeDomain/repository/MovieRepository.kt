package com.example.a36_retrofit.domain.homeDomain.repository

import androidx.paging.Pager
import com.example.a36_retrofit.data.database.entities.MovieDetailsEntity
import com.example.a36_retrofit.data.database.entities.MovieEntity
import com.example.a36_retrofit.data.utils.DataState
import com.example.a36_retrofit.domain.homeDomain.model.MovieItem

interface MovieRepository {
    //popular movies domain
    suspend fun getMoviesPopularFromApi(offset:Int):DataState<List<MovieItem>>
    fun getMoviesPopularPager(): Pager<Int, MovieItem>
    //popular movies room
    suspend fun getMoviesPopularFromLocal(search:String): DataState<List<MovieItem>>
    suspend fun insertMovies(list:List<MovieEntity>)





    suspend fun getMovieTopRatedFromApi():DataState<List<MovieItem>>

    suspend fun clearDetailMovie()

    suspend fun clearMovies()

}