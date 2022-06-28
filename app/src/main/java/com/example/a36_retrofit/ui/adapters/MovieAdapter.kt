package com.example.a36_retrofit.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a36_retrofit.databinding.MovieItemBinding
import com.example.a36_retrofit.domain.model.Movie
import com.example.a36_retrofit.ui.ext.load
import java.util.*


class MovieAdapter(private val movieList: List<Movie>, private val listener: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    private var items: MutableList<Movie> =ArrayList()

    init{
//        items= ArrayList()
        items.addAll(movieList)
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.render(items[position], listener)
    }

    override fun getItemCount(): Int = items.size


    fun filter(strSearch: String) {
        items.clear()
        if (strSearch.isEmpty()) {
            items.addAll(movieList)
        } else {
            for (item in movieList) {
                    if (item.title.lowercase(Locale.getDefault()).contains(strSearch)) {
                        items.add(item)
                    }
                }
        }
        notifyDataSetChanged()
    }


    class MovieHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun render(movie: Movie, listener: (Movie) -> Unit) {
            binding.textView.text = movie.title


            binding.imageView.load(movie.poster_path,binding.imagePendingAnimation )
            binding.root.setOnClickListener { listener(movie) }

        }
    }
}


