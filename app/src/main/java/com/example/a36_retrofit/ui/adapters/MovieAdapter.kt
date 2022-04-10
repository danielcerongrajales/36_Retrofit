package com.example.a36_retrofit.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a36_retrofit.data.model.Result
import com.example.a36_retrofit.databinding.MovieItemBinding

class MovieAdapter(private val MovieList: List<Result>, private val listener: (Result) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.render(MovieList[position], listener)
    }

    override fun getItemCount(): Int = MovieList.size


    class MovieHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun render(Movie: Result, listener: (Result) -> Unit) {
            binding.textView.text = Movie.title
            Glide.with(binding.root).load("https://image.tmdb.org/t/p/w500${Movie.poster_path}").centerCrop().into(binding.imageView);
            binding.root.setOnClickListener { listener(Movie) }

        }
    }
}