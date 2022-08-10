package com.example.a36_retrofit.data.network.model

data class KeywordByQuery(
    val page: Int,
    val results: List<ListKeywords>,
    val total_pages: Int,
    val total_results: Int
)