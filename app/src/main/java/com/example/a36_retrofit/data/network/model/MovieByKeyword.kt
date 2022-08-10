package com.example.a36_retrofit.data.network.model

data class MovieByKeyword(
    val id: Int,
    val page: Int,
    val results: List<MovieResultModel>,
    val total_pages: Int,
    val total_results: Int
)