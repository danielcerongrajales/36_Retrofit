package com.example.a36_retrofit.di

import androidx.lifecycle.SavedStateHandle
import com.example.a36_retrofit.data.model.MovieResul
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
object DetailsIdModule {
        @Provides
        @Named("movieId")
        fun movieIdProvider(stateHandle: SavedStateHandle): MovieResul =
            stateHandle.get<MovieResul>("peli")
                ?: throw IllegalStateException("Movie Id not found in the state handle")

}