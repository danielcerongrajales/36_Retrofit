package com.example.a36_retrofit.data.model

data class PopularMovies(
    val page: Int?=0,
    val results: List<MovieResul>?= emptyList(),
    val total_pages: Int?=0,
    val total_results: Int?=0
)