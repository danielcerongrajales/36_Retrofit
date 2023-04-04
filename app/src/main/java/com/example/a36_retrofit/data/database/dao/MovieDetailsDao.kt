package com.example.a36_retrofit.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.a36_retrofit.data.database.entities.MovieDetailsEntity

@Dao
interface MovieDetailsDao {

    @Query("SELECT * FROM movieDetailsTable WHERE id LIKE :query")
    suspend fun getDetailsMovie(query: Int):MovieDetailsEntity

//    @Query("SELECT * FROM movieResultTable WHERE label LIKE :query")
//    fun pagingSource(query: String): PagingSource<Int, User>
//    @Query("SELECT * FROM movieResultTable ORDER BY popularity DESC")
//    fun pagingSource(): PagingSource<Int, Movie>

    @Insert
    suspend fun insertMovie(movie:MovieDetailsEntity)

    @Query("DELETE FROM movieDetailsTable")
    suspend fun deleteAllMovies()
}