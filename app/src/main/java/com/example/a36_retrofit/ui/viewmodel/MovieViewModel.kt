package com.example.a36_retrofit.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.a36_retrofit.data.model.PopularMovies
import com.example.a36_retrofit.domain.GetPopularMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {
    val movieModel=MutableLiveData<PopularMovies>()
    var getPopularMoviesUseCase= GetPopularMoviesUseCase()

    fun onCreate(){
        viewModelScope.launch(Dispatchers.IO){
            val result=getPopularMoviesUseCase()
            movieModel.postValue(result)
        }
    }
}