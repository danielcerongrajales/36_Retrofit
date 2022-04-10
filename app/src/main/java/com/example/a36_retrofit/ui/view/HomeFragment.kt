package com.example.a36_retrofit.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import at.grabner.circleprogress.TextMode
import com.example.a36_retrofit.databinding.FragmentHomeBinding
import com.example.a36_retrofit.ui.viewmodel.MovieViewModel


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(this.context, 3)
        // Inflate the layout for this fragment
        movieViewModel.onCreate()
        movieViewModel.movieModel.observe(viewLifecycleOwner) { current ->
            Log.d("tag", "onHeroCLick: $current")
//            binding.recyclerView.layoutManager= LinearLayoutManager(this.context)
            val adapter= current.keywords.let {
                Log.d("tag", "onHeroCLick: $it")
        //                MovieAdapter(it) { item ->
        //                    Log.d("tag", "onHeroCLick: $item")
                        }
//            }
            binding.circleView.spin(); // start spinning
//        mCircleView.stopSpinning(); // stops spinning. Spinner gets shorter until it disappears.
//            binding.circleView.mAnimationDuration=800
            binding.circleView.setUnitVisible(false)
            binding.circleView.setTextMode(TextMode.TEXT)
            binding.circleView.setShowTextWhileSpinning(true)
            binding.circleView.setText("Loading...")
//
//            binding.recyclerView.adapter= adapter
        }
        return binding.root


    }



}