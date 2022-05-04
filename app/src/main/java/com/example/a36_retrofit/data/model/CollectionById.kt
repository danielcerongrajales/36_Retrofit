package com.example.a36_retrofit.data.model

data class CollectionById(
    val backdrop_path: String,
    val id: Int,
    val name: String,
    val overview: String,
    val parts: List<MovieResul>,
    val poster_path: String
)