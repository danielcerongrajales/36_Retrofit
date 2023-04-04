package com.example.a36_retrofit.data.network.model

data class CollectionById(
    val backdrop_path: String,
    val id: Int,
    val name: String,
    val overview: String,
    val parts: List<MovieResultModel>,
    val poster_path: String
)