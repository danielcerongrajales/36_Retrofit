package com.example.a36_retrofit.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a36_retrofit.domain.detailsDomain.model.MovieDetailsItem

@Entity(tableName="movieDetailsTable")
data class MovieDetailsEntity(
    val adult: Boolean,
    val backdrop_path: String,
//    val belongs_to_collection: BelongsToCollection,
    val budget: Int,
//    val genres: List<Genre>,
    val homepage: String,
    @PrimaryKey val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
//    val production_companies: List<ProductionCompany>,
//    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
//    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

fun MovieDetailsItem.toDatabase()=MovieDetailsEntity(
 adult,
 backdrop_path,
// belongs_to_collection,
 budget,
// genres,
 homepage,
 id,
 imdb_id,
 original_language,
 original_title,
 overview,
 popularity,
poster_path,
// production_companies,
// production_countries,
 release_date,
 revenue,
 runtime,
// spoken_languages,
 status,
 tagline,
 title,
 video,
 vote_average,
 vote_count)