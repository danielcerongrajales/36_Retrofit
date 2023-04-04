package com.example.a36_retrofit.ui.homeScreen.viewmodel

import androidx.lifecycle.*
import androidx.paging.*
import com.example.a36_retrofit.data.utils.DataState
import com.example.a36_retrofit.domain.homeDomain.useCase.GetPopularMoviesUseCase
import com.example.a36_retrofit.domain.homeDomain.model.MovieItem
import com.example.a36_retrofit.data.utils.StateMessage
import com.example.a36_retrofit.domain.homeDomain.useCase.GetTopRatedMoviesUseCase
import com.example.a36_retrofit.ui.utils.ConnectionFlow
import com.example.a36_retrofit.ui.utils.ConnectivityManag
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
 class MovieViewModel @Inject constructor(val getPopularMoviesUseCase: GetPopularMoviesUseCase, val getTopRatedMovieUseCase: GetTopRatedMoviesUseCase, val connectivityManag: ConnectivityManag,val  networkStatusTracker: ConnectionFlow,) :
    ViewModel() {
    sealed class MyState {
        object Fetched : MyState()
        object Error : MyState()
    }



    private val _stateTwo = MutableStateFlow(UiStateTwo())
    val stateTwo : StateFlow<UiStateTwo> get() = _stateTwo

 var popularMovies: Flow<PagingData<MoviesItemUiState>> = getPopularMoviesUseCase()
     .cachedIn(viewModelScope)
     .map { pagingData ->
         pagingData.map {
//                        Log.d("tag","${it.title}, ${it.popularity}")
             it.toUiState()
         }
     }

data class UiStateTwo(
//    var popularMovies: PagingData<MoviesItemUiState> ?=null,
    val loadingBar:Boolean=false,
    val topRatedMovies:List<MoviesItemUiState>?=emptyList(),
    val stateMessage:StateMessage?=StateMessage()
)




    init {
        viewModelScope.launch  {
            loadTopRatedMovies()


        }



            }

    private suspend fun loadTopRatedMovies() {
        _stateTwo.value = _stateTwo.value.copy(loadingBar = true)
        when (val a = getTopRatedMovieUseCase()) {
            is DataState.Data -> {
                _stateTwo.value = _stateTwo.value.copy(
                    stateMessage = a.response,
                    topRatedMovies = a.data?.map { it.toUiState() })
            }
            is DataState.Error -> {
                _stateTwo.value = _stateTwo.value.copy(stateMessage = a.response,
                    topRatedMovies = a.data?.map { it.toUiState() })

            }
        }





        _stateTwo.value = _stateTwo.value.copy(loadingBar = false)
    }


//private fun Update( lista: List<MoviesItemUiState>){
//    _stateTwo.value = _stateTwo.value.copy(topRatedMovies  =lista)
//}





    private fun MovieItem.toUiState() = MoviesItemUiState(
        this,
        onBookmark = {
            viewModelScope.launch {
//                switchMovieFavoriteUseCase(this@toUiState)
            }
        }
//        onShare = { TODO() },
//        onDelete = { TODO() }
    )

    fun changeLoading() {
        viewModelScope.launch {
            _stateTwo.value= _stateTwo.value.copy(loadingBar = true)

        }

    }

    fun reload() {
        viewModelScope.launch  {
            loadTopRatedMovies()
        }

    }


}


data class MoviesItemUiState(
    val movieItem: MovieItem,
    val onBookmark: () -> Unit
)

