package com.example.a36_retrofit.domain.homeDomain.model

import android.os.Parcelable
import com.example.a36_retrofit.data.database.entities.MovieEntity
import com.example.a36_retrofit.data.network.model.MovieResultModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieItem (
    val adult: Boolean,
    val backdrop_path: String?="null",
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?="null",
    val release_date: String?="null",
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
    ): Parcelable

fun MovieResultModel.toDomain()=MovieItem(
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
    vote_count)



fun MovieEntity.toDomain()=MovieItem(
    adult=adult,
    backdrop_path= backdrop_path,
    genre_ids= genre_ids,
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