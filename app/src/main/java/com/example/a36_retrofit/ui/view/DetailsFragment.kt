package com.example.a36_retrofit.ui.view

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.a36_retrofit.data.model.MovieResul
import com.example.a36_retrofit.databinding.FragmentDetailsBinding
import com.example.a36_retrofit.ui.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    companion object {
        val MOVIE:Int = -1
    }
    private val detailsViewModel: DetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
//
//        var index: Result = arguments?.getParcelable<Result>("peli")
//       index.getParcelableExtra
        detailsViewModel.onCreate()
        detailsViewModel.movieById.observe(viewLifecycleOwner) {

            Toast.makeText(context, it.overview, Toast.LENGTH_SHORT).show()
        }

        val serializableDataClass =  arguments?.getParcelable<Parcelable>("peli")
        val dataClass = serializableDataClass as MovieResul
        Log.d("TAG", "onCreate $MOVIE y $dataClass}")
        return binding.root
    }


}