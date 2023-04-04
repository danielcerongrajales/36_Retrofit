package com.example.a36_retrofit.ui.homeScreen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a36_retrofit.databinding.TopRatedMovieItemBinding
import com.example.a36_retrofit.ui.utils.loadCoiler
import com.example.a36_retrofit.ui.homeScreen.viewmodel.MoviesItemUiState


class ViewPaggerMovieAdapter() :
    ListAdapter<MoviesItemUiState, ViewPaggerMovieAdapter.MovieHolder>(MovieComparator){
    //    RecyclerView.Adapter<MovieAdapterSecond.MovieHolder>() {
    companion object {
        private val MovieComparator = object : DiffUtil.ItemCallback<MoviesItemUiState>() {
            override fun areItemsTheSame(oldItem: MoviesItemUiState, newItem: MoviesItemUiState): Boolean {

                return (
                        oldItem.movieItem.id == newItem.movieItem.id)
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
            TopRatedMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        getItem(position)?.let { holder.render(it) }
    }



    class MovieHolder(private val binding: TopRatedMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun render(movie: MoviesItemUiState) {
//            binding.textView6.text = movie.name
//
//                binding.imageView4.visibility= VISIBLE
//            if(!movie.profile_path.isNullOrEmpty()){
            binding.imageView.loadCoiler(movie.movieItem.poster_path!!,binding.imagePendingAnimation,"#1339C1" )

//            movie.profile_path.let{
//                    binding.imageView4.loadCoil(it,binding.imagePendingAnimation2)
//                binding.imageView4.loadCoiler(it,binding.imagePendingAnimation2,"#850914")
//                    binding.imageView4.loadGlide(it,binding.imagePendingAnimation2)
//            }
//            }



//            binding.root.setOnClickListener { listener(ActionUI.Click(movie)) }

        }
//        fun clearImage() {
//            binding.imageView4.dispose()
//        }
    }


//    override fun onViewRecycled(holder: MovieHolder) {
//        holder.clearImage()
//    }
}


