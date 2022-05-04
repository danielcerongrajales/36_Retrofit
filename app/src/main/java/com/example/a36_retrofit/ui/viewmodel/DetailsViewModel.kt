package com.example.a36_retrofit.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a36_retrofit.data.model.*
import com.example.a36_retrofit.domain.GetMovieByIdUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailsViewModel @Inject constructor(@Named("movieId") private val id: MovieResul, val getMovieByIdUseCase: GetMovieByIdUseCase): ViewModel() {
    val movieById=MutableLiveData<MovieById>()
//    val movieModel=MutableLiveData<KeywordByQuery>()
//    val movieModel=MutableLiveData<MovieByKeyword>()
//    val movieModel=MutableLiveData<MovieById>()
//val movieModel=MutableLiveData<CollectionById>()
//val movieModel=MutableLiveData<KeywordsMovieById>()



    fun onCreate(){
        viewModelScope.launch(Dispatchers.IO){
//            val result=getKeywordsMovieByIdUseCase("385103")
//            movieModel.postValue(result)
            val result=getMovieByIdUseCase(id.id)
            movieById.postValue(result)


        }
    }
}