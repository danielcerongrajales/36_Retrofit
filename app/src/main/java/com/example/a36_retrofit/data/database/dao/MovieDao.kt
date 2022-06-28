package com.example.a36_retrofit.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.a36_retrofit.data.database.entities.MovieResultEntity
@Dao
interface MovieDao {
    @Query("SELECT * FROM movieResultTable ORDER BY title DESC")
    suspend fun getAllMovies():List<MovieResultEntity>

    @Insert
    suspend fun insertAll(movies:List<MovieResultEntity>)

    @Query("DELETE FROM movieResultTable")
    suspend fun deleteAllMovies()
}