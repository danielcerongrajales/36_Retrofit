package com.example.a36_retrofit.domain.model

import android.os.Parcelable
import com.example.a36_retrofit.data.database.entities.MovieResultEntity
import com.example.a36_retrofit.data.model.MovieResultModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    val adult: Boolean,
    val backdrop_path: String,
//    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
    ): Parcelable
fun MovieResultModel.toDomain()=Movie(
    adult,
    backdrop_path,
//    genre_ids,
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
    vote_count)

fun MovieResultEntity.toDomain()=Movie(
    adult=adult,
    backdrop_path= backdrop_path,
//    genre_ids= genre_ids,
    id=id,
    original_language=original_language,
    original_title=original_title,
    overview=overview,
    popularity=popularity,
    poster_path=poster_path,
    release_date=release_date,
    title=title,
    video=video,
    vote_average=vote_average,
    vote_count=vote_count)