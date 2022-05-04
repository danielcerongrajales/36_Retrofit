package com.example.a36_retrofit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a36_retrofit.core.Respuesta

import com.example.a36_retrofit.domain.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MovieViewModel @Inject constructor(val getPopularMoviesUseCase: GetPopularMoviesUseCase): ViewModel() {
    val popularMovies=MutableLiveData<Respuesta>()

//    val movieModel=MutableLiveData<KeywordByQuery>()
//    val movieModel=MutableLiveData<MovieByKeyword>()
//    val movieModel=MutableLiveData<MovieById>()
//val movieModel=MutableLiveData<CollectionById>()
//val movieModel=MutableLiveData<KeywordsMovieById>()



     init{
        viewModelScope.launch(Dispatchers.IO){
//            val result=getKeywordsMovieByIdUseCase("385103")
//            movieModel.postValue(result)
//            is Respuesta.Loading->{
//            Log.d("tag", "loading")
            popularMovies.postValue(Respuesta.Loading())
//        }
            getPopularMoviesUseCase().let {current->

                when(current){
                    is Respuesta.Success ->{
                        Log.d("tag", "succes")
                        popularMovies.postValue(Respuesta.Success(current.popularMovies))
                    }
                    is Respuesta.HttpErrors.ResourceForbidden -> popularMovies.postValue(Respuesta.Failure(current.exception))
                    is Respuesta.HttpErrors.ResourceNotFound -> popularMovies.postValue(Respuesta.Failure(current.exception))
                    is Respuesta.HttpErrors.InternalServerError ->  popularMovies.postValue(Respuesta.Failure(current.exception))
                    is Respuesta.HttpErrors.BadGateWay ->  popularMovies.postValue(Respuesta.Failure(current.exception))
                    is Respuesta.HttpErrors.ResourceRemoved -> popularMovies.postValue(Respuesta.Failure(current.exception))
                    is Respuesta.HttpErrors.RemovedResourceFound ->  popularMovies.postValue(Respuesta.Failure(current.exception))

                    is Respuesta.Failure -> popularMovies.postValue( Respuesta.Failure(current.error))
                    is Respuesta.NetworkException ->  popularMovies.postValue( Respuesta.Failure(current.error))
                }

//
//                when(current){
//
//                    is Respuesta.HttpErrors -> {
//                        popularMovies.postValue(Respuesta.HttpErrors(current.))
//                    }
//                    is Respuesta.Success->{
//                        Log.d("tag", "succes")
//                        popularMovies.postValue(Respuesta.Success(current.popularMovies))
//                    }
//                    else -> {}
//                }
//                when (it.){
//                    is Result.Success ->{}
//                }
//                if(it){
//                    popularMovies.value=Respuesta.Success(result)
//
//                }else{
//                    popularMovies.value=Respuesta.Failure(IllegalStateException("Movie Id not found in the state handle"))
//                }
            }


//            popularMovies.postValue(result)
        }
    }

}