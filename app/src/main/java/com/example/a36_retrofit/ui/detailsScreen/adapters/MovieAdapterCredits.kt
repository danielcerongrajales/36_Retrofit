package com.example.a36_retrofit.ui.detailsScreen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.dispose
import com.example.a36_retrofit.databinding.CastItemBinding
import com.example.a36_retrofit.domain.detailsDomain.model.CastItem
import com.example.a36_retrofit.ui.utils.loadCoiler


class MovieAdapterCredits() :
    ListAdapter<CastItem, MovieAdapterCredits.MovieHolder>(MovieComparator){
//    RecyclerView.Adapter<MovieAdapterSecond.MovieHolder>() {
companion object {
    private val MovieComparator = object : DiffUtil.ItemCallback<CastItem>() {
        override fun areItemsTheSame(oldItem: CastItem, newItem: CastItem): Boolean {

            return (
                    oldItem.id == newItem.id) ||
                    ( oldItem.name == newItem.name)
        }

        override fun areContentsTheSame(oldItem: CastItem, newItem: CastItem): Boolean =
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
            CastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        getItem(position)?.let { holder.render(it) }
    }



    class MovieHolder(private val binding: CastItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun render(movie: CastItem) {
            binding.textView6.text = movie.name
//
//                binding.imageView4.visibility= VISIBLE
//            if(!movie.profile_path.isNullOrEmpty()){

                movie.profile_path.let{
//                    binding.imageView4.loadCoil(it,binding.imagePendingAnimation2)
                    binding.imageView4.loadCoiler(it,binding.imagePendingAnimation2,"#850914")
//                    binding.imageView4.loadGlide(it,binding.imagePendingAnimation2)
                }
//            }



//            binding.root.setOnClickListener { listener(ActionUI.Click(movie)) }

        }
        fun clearImage() {
            binding.imageView4.dispose()
        }
    }
//    override fun onViewRecycled(holder: MovieHolder) {
//        holder.clearImage()
//    }
}


