package com.example.a36_retrofit.ui.detailsScreen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a36_retrofit.databinding.FragmentDetailsBinding
import com.example.a36_retrofit.ui.detailsScreen.adapters.MovieAdapterCredits
import com.example.a36_retrofit.ui.utils.loadCoiler
import com.example.a36_retrofit.ui.detailsScreen.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapterCredits: MovieAdapterCredits

    companion object {
        const val MOVIE: Int = -1
    }

    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        movieAdapterCredits = MovieAdapterCredits()
//        var index: Result = arguments?.getParcelable<Result>("peli")
//       index.getParcelableExtra
//        detailsViewModel.onCreate()

        binding.recyclerView2.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = movieAdapterCredits

        }

        detailsViewModel.movieById.observe(viewLifecycleOwner) {

            binding.imageView2.loadCoiler(it.backdrop_path,binding.imagePendingAnimation2,"#1339C1")
            binding.imageView3.loadCoiler(it.poster_path,binding.imagePendingAnimation2,"#1339C1")
            binding.textVieww3.text = it.title
            binding.textView4.text = "${it.vote_count}"
            binding.textView5.text = it.overview

            binding.textVieww3.visibility = VISIBLE
            binding.textView4.visibility = VISIBLE
            binding.textView5.visibility = VISIBLE

            binding.materialCardView2.visibility = VISIBLE
            binding.shimmerad.shimmer.stopShimmer()
            binding.shimmercontainer.visibility = View.GONE
        }
        detailsViewModel.movieCast.observe(viewLifecycleOwner) {

            movieAdapterCredits.submitList(it.cast)
            binding.recyclerView2.visibility = VISIBLE
        }
//        val serializableDataClass = arguments?.getParcelable<Parcelable>("popularMovie")
//        val dataClass = serializableDataClass as Movie
//        Log.d("TAG", "onCreate $MOVIE y $dataClass}")
        return binding.root
    }


}