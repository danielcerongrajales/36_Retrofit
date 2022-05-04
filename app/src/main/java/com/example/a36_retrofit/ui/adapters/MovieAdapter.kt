package com.example.a36_retrofit.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a36_retrofit.data.model.MovieResul
import com.example.a36_retrofit.databinding.MovieItemBinding
import com.example.a36_retrofit.ui.ext.load

class MovieAdapter(private val movieList: List<MovieResul>, private val listener: (MovieResul) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.render(movieList[position], listener)
    }

    override fun getItemCount(): Int = movieList.size


    class MovieHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun render(movie: MovieResul, listener: (MovieResul) -> Unit) {
            binding.textView.text = movie.title
            binding.imageView.load(movie.poster_path)
            binding.root.setOnClickListener { listener(movie) }

        }
    }
}