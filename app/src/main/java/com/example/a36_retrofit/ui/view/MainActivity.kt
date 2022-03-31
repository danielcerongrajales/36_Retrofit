package com.example.a36_retrofit.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.a36_retrofit.R
import com.example.a36_retrofit.ui.viewmodel.MovieViewModel


class MainActivity : AppCompatActivity() {
    private val movieViewModel: MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieViewModel.onCreate()
       movieViewModel.movieModel.observe(this) { current ->
           Log.d("TAG", "onCreate ${current.results}")
       }
    }
}