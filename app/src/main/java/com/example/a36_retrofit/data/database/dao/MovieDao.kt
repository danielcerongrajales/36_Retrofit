package com.example.a36_retrofit.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.a36_retrofit.data.database.entities.MovieEntity
import com.example.a36_retrofit.domain.homeDomain.model.MovieItem

@Dao
interface MovieDao {
    @Query("SELECT * FROM movieEntityTable ORDER BY title DESC")
    suspend fun getAllMovies():List<MovieEntity>


    @Query("SELECT * FROM movieEntityTable WHERE id LIKE :query")
    fun getMovieById(query: Int): List<MovieEntity>

    @Query("SELECT * FROM movieEntityTable ORDER BY popularity DESC")
    fun pagingSource(): PagingSource<Int, MovieItem>

//    @Insert (onConflict = OnConflictStrategy.REPLACE)
@Insert
suspend fun insertAll(movies:List<MovieEntity>)

    @Query("DELETE FROM movieEntityTable")
    suspend fun deleteAllMovies()
}