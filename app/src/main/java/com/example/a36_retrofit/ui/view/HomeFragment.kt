package com.example.a36_retrofit.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.a36_retrofit.R
import com.example.a36_retrofit.core.Respuesta
import com.example.a36_retrofit.databinding.FragmentHomeBinding
import com.example.a36_retrofit.ui.adapters.MovieAdapter
import com.example.a36_retrofit.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(this.context, 3)
        setObservers()
        return binding.root
    }

    private fun setObservers() {
        movieViewModel.popularMovies.observe(viewLifecycleOwner) { current ->
            when (current) {
                is Respuesta.Loading -> {
                    Log.d("tag", "loading")
                    binding.circleView.spin() // start spinning
                }
                is Respuesta.Failure -> {
                    Log.d("tag", current.error)
                }
                is Respuesta.Success -> {
                    Log.d("tag", "success")
                    binding.circleView.stopSpinning()
                    binding.circleView.visibility = View.GONE
                    binding.textView2.visibility = View.GONE
                    binding.recyclerView.adapter =
                        current.popularMovies.results.let {
                            MovieAdapter(it!!) { item ->

//                            val res:Result=item
                                val bundle = bundleOf("peli" to item)
                                this.findNavController()
                                    .navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
                            }
                        }
//            Log.d("tag", "onHeroCLick: $current")
//            binding.recyclerView.layoutManager= LinearLayoutManager(this.context)
//            val adapter= current.keywords.let {
                }
            }
        }
    }
//            binding.circleView.spin() // start spinning
//        mCircleView.stopSpinning(); // stops spinning. Spinner gets shorter until it disappears.
//            binding.circleView.mAnimationDuration=800
//            binding.circleView.setUnitVisible(false)
//            binding.circleView.setTextMode(TextMode.TEXT)
//            binding.circleView.setShowTextWhileSpinning(true)
//            binding.circleView.setText("Loading...")
//


}