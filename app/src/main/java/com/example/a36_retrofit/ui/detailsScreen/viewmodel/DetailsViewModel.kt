package com.example.a36_retrofit.ui.detailsScreen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.a36_retrofit.data.utils.DataState
import com.example.a36_retrofit.data.utils.StateMessage
import com.example.a36_retrofit.domain.detailsDomain.model.CreditsItem
import com.example.a36_retrofit.domain.detailsDomain.model.MovieDetailsItem
import com.example.a36_retrofit.domain.detailsDomain.useCase.GetMovieByIdUseCase
import com.example.a36_retrofit.domain.homeDomain.model.MovieItem
import com.example.a36_retrofit.ui.homeScreen.viewmodel.MovieViewModel
import com.example.a36_retrofit.ui.homeScreen.viewmodel.MoviesItemUiState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailsViewModel @Inject constructor(@Named("movieId") private val id: MovieItem, private val getMovieByIdUseCase: GetMovieByIdUseCase): ViewModel() {
    private val _stateTwo = MutableStateFlow(UiStateTwo())
    val stateTwo : StateFlow<UiStateTwo> get() = _stateTwo

    val movieById=MutableLiveData<MovieDetailsItem>()
    val movieCast=MutableLiveData<CreditsItem>()

//    val movieModel=MutableLiveData<KeywordByQuery>()
//    val movieModel=MutableLiveData<MovieByKeyword>()
//    val movieModel=MutableLiveData<MovieById>()
//val movieModel=MutableLiveData<CollectionById>()
//val movieModel=MutableLiveData<KeywordsMovieById>()

    data class UiStateTwo(
        val loadingBar:Boolean=false,
        val popularMovies: Flow<PagingData<MoviesItemUiState>>?=null,
        val stateMessage: StateMessage?= StateMessage()
    )

init{
    _stateTwo.value= _stateTwo.value.copy(loadingBar = true)
    viewModelScope.launch {
        getMovieByIdUseCase.invoke(id.id)
        when(val a=getMovieByIdUseCase.moviesNetwork ){
            is DataState.Data ->  movieById.postValue(a.data)
            is DataState.Error -> TODO()
        }
        when(val a=getMovieByIdUseCase.movieCast ){
            is DataState.Data ->  movieCast.postValue(a.data)
            is DataState.Error -> TODO()
        }

//        _stateTwo.value = _stateTwo.value.copy(popularMovies = getMovieByIdUseCase(id.id).flow
//            .cachedIn(viewModelScope).map { pagingData ->
//                pagingData.map {
//                    it.toUiState()
//                }
//            }
//        )

    }
    _stateTwo.value= _stateTwo.value!!.copy(loadingBar = false)

}
//    fun onCreate(){
//        viewModelScope.launch(Dispatchers.IO){
////            val result=getKeywordsMovieByIdUseCase("385103")
////            movieModel.postValue(result)
//            val result=getMovieByIdUseCase(id.id)
//            movieById.postValue(result)
//
//
//        }
//    }
}