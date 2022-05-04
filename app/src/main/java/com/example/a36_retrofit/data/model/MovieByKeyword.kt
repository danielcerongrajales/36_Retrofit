package com.example.a36_retrofit.data.model

data class MovieByKeyword(
    val id: Int,
    val page: Int,
    val results: List<MovieResul>,
    val total_pages: Int,
    val total_results: Int
)