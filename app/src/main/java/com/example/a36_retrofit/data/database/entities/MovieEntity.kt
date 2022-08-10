package com.example.a36_retrofit.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a36_retrofit.domain.homeDomain.model.MovieItem


@Entity(tableName="movieEntityTable")
data class MovieEntity(

    @ColumnInfo(name="adult") val adult: Boolean,
    @ColumnInfo(name="backdrop_path") val backdrop_path: String?,
    @ColumnInfo(name="genre_ids") val genre_ids:List<Int>,
    @ColumnInfo(name="id")  @PrimaryKey val id: Int,
    @ColumnInfo(name="original_language") val original_language: String,
    @ColumnInfo(name="original_title") val original_title: String,
    @ColumnInfo(name="overview") val overview: String,
    @ColumnInfo(name="popularity") val popularity: Double,
    @ColumnInfo(name="poster_path") val poster_path: String?="null",
    @ColumnInfo(name="release_date") val release_date: String?,
    @ColumnInfo(name="title") val title: String,
    @ColumnInfo(name="video") val video: Boolean,
    @ColumnInfo(name="vote_average") val vote_average: Double,
    @ColumnInfo(name="vote_count") val vote_count: Int


)
fun MovieItem.toDatabase()=MovieEntity(
        adult,
        backdrop_path,
        genre_ids,
        id,
        original_language,
        original_title,
        overview,
        popularity,
        poster_path,
        release_date,
        title,
        video,
        vote_average,
        vote_count
    )

