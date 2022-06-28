package com.example.a36_retrofit.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.a36_retrofit.domain.GetPopularMoviesUseCase
import com.example.a36_retrofit.domain.model.Movie
import com.example.a36_retrofit.data.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MovieViewModel @Inject constructor(val getPopularMoviesUseCase: GetPopularMoviesUseCase): ViewModel() {
    val popularMovies=MutableLiveData<DataState<List<Movie>>>()

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
            popularMovies.postValue(DataState.Loading())
//        }
            getPopularMoviesUseCase().let {current->
                popularMovies.postValue(current)
//                when(current){
//                    is RespuestaItem.Success ->{
//                        Log.d("tag", "succes")
//                        popularMovies.postValue(RespuestaItem.Success(current.popularMovies))
//                    }
//                    is RespuestaItem.HttpErrors.ResourceForbidden -> popularMovies.postValue(RespuestaItem.Failure(current.exception))
//                    is RespuestaItem.HttpErrors.ResourceNotFound -> popularMovies.postValue(RespuestaItem.Failure(current.exception))
//                    is RespuestaItem.HttpErrors.InternalServerError ->  popularMovies.postValue(
//                        RespuestaItem.Failure(current.exception))
//                    is RespuestaItem.HttpErrors.BadGateWay ->  popularMovies.postValue(RespuestaItem.Failure(current.exception))
//                    is RespuestaItem.HttpErrors.ResourceRemoved -> popularMovies.postValue(RespuestaItem.Failure(current.exception))
//                    is RespuestaItem.HttpErrors.RemovedResourceFound ->  popularMovies.postValue(
//                        RespuestaItem.Failure(current.exception))
//
//                    is RespuestaItem.Failure -> popularMovies.postValue( RespuestaItem.Failure(current.error))
//                    is RespuestaItem.NetworkException ->  popularMovies.postValue( RespuestaItem.Failure(current.error))
//                }

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