package com.example.a36_retrofit.data.database.entities

import androidx.room.ColumnInfo

data class PopularMoviesEntity(
    @ColumnInfo(name="page") val page: Int?=0,
    val userCreatorId: Long,
//    @ColumnInfo(name="results") val results: List<MovieResulEntity>?= emptyList(),
    @ColumnInfo(name="total_pages") val total_pages: Int?=0,
    @ColumnInfo(name="total_results") val total_results: Int?=0
)