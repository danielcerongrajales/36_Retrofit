package com.example.a36_retrofit.domain

import com.example.a36_retrofit.data.MovieRepository
import com.example.a36_retrofit.data.model.*

class GetKeywordByQueryUseCase {
    private val repository=MovieRepository()
    suspend operator fun invoke(query:String): KeywordByQuery =repository.getKeywordByQuery(query)


}