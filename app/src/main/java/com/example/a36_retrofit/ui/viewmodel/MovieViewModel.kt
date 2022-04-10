package com.example.a36_retrofit.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a36_retrofit.data.model.*
import com.example.a36_retrofit.domain.GetKeywordsMovieByIdUseCase

import com.example.a36_retrofit.domain.GetPopularMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {
//    val movieModel=MutableLiveData<PopularMovies>()
//    val movieModel=MutableLiveData<KeywordByQuery>()
//    val movieModel=MutableLiveData<MovieByKeyword>()
//    val movieModel=MutableLiveData<MovieById>()
//val movieModel=MutableLiveData<CollectionById>()
val movieModel=MutableLiveData<KeywordsMovieById>()

    var getKeywordsMovieByIdUseCase= GetKeywordsMovieByIdUseCase()

    fun onCreate(){
        viewModelScope.launch(Dispatchers.IO){
            val result=getKeywordsMovieByIdUseCase("385103")
            movieModel.postValue(result)
        }
    }
}