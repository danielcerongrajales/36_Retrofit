package com.example.a36_retrofit.ui.homeScreen.adapters.pagging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a36_retrofit.databinding.MovieItemBinding
import com.example.a36_retrofit.ui.utils.loadCoiler
import com.example.a36_retrofit.ui.homeScreen.view.ActionUI
import com.example.a36_retrofit.ui.homeScreen.viewmodel.MoviesItemUiState


class MovieAdapter(private val listener: (ActionUI) -> Unit) :
    PagingDataAdapter<MoviesItemUiState, MovieAdapter.MovieHolder>(MovieComparator){
//    RecyclerView.Adapter<MovieAdapterSecond.MovieHolder>() {
companion object {
    private val MovieComparator = object : DiffUtil.ItemCallback<MoviesItemUiState>() {
        override fun areItemsTheSame(oldItem: MoviesItemUiState, newItem: MoviesItemUiState): Boolean {
//Log.d("tag","${oldItem.movieItem.title}, ${ newItem.movieItem.title}")
            return (oldItem.movieItem.id == newItem.movieItem.id)
        }

        override fun areContentsTheSame(oldItem: MoviesItemUiState, newItem: MoviesItemUiState): Boolean =
            oldItem == newItem
    }
}

//    private var items: MutableList<MoviesItemUiState> =ArrayList()

//    init{
//        items= ArrayList()
//        items.addAll(movieList)
//}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        getItem(position)?.let { holder.render(it, listener) }
    }

//    override fun getItemCount(): Int = items.size


//    fun filter(strSearch: String) {
//        items.clear()
//        if (strSearch.isEmpty()) {
//            items.addAll(movieList)
//        } else {
//            for (item in movieList) {
//                    if (item.movie.title.lowercase(Locale.getDefault()).contains(strSearch)) {
//                        items.add(item)
//                    }
//                }
//        }
//        notifyDataSetChanged()
//    }


    class MovieHolder(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun render(movie: MoviesItemUiState, listener: (ActionUI) -> Unit) {
            binding.textView.text = movie.movieItem.title
//          Log.d("tag", movie.movieItem.toString())
//            Log.d("tag","${movie.movieItem.id}")
//Log.d("tag",movie.movie.title)
//            binding.imageView.visibility= GONE
                         binding.imageView.loadCoiler(movie.movieItem.poster_path!!,binding.imagePendingAnimation,"#1339C1" )
//            binding.imageView.load("https://image.tmdb.org/t/p/w500${movie.movieItem.poster_path!!}"){
//                scale(Scale.FILL)
//            }
//            binding.imagePendingAnimation.loadCoil(movie.movieItem.poster_path!! )
            binding.root.setOnClickListener { listener(ActionUI.Click(movie.movieItem)) }

        }
    }
}


