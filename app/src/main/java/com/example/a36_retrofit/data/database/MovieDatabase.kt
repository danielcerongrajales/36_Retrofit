package com.example.a36_retrofit.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.a36_retrofit.data.database.dao.MovieDao
import com.example.a36_retrofit.data.database.entities.MovieResultEntity


@Database(entities=[MovieResultEntity::class],version=1)
abstract class MovieDatabase:RoomDatabase() {
    abstract fun getMovieDao(): MovieDao

}