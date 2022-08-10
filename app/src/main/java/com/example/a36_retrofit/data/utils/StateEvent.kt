package com.example.a36_retrofit.data.utils

import com.example.a36_retrofit.ui.homeScreen.viewmodel.MoviesItemUiState

interface StateEvent {

    fun errorInfo(): String
    fun errorInfos(): MoviesItemUiState
    fun eventName(): String
    fun shouldDisplayProgressBar(): Boolean
}
