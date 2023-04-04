package com.example.a36_retrofit.ui.homeScreen.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.a36_retrofit.R
import com.example.a36_retrofit.databinding.FragmentHomeBinding
import com.example.a36_retrofit.domain.homeDomain.model.MovieItem
import com.example.a36_retrofit.ui.homeScreen.adapters.ViewPaggerMovieAdapter
import com.example.a36_retrofit.ui.homeScreen.adapters.pagging.MovieAdapter
import com.example.a36_retrofit.ui.homeScreen.adapters.pagging.MovieLoadStateAdapter
import com.example.a36_retrofit.ui.homeScreen.adapters.viewpager.HorizontalMarginItemDecoration
import com.example.a36_retrofit.ui.homeScreen.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.lang.Math.abs


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieAdapter2: ViewPaggerMovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)



      movieViewModel.connectivityManag.isNetworkAvailable2.observe(this){
          if(it.internet){

              if(it.proxy){
                  Toast.makeText(this.context, "no se admite proxy ", Toast.LENGTH_SHORT).show()

              }

          }else {

          }
      }



        movieAdapter = MovieAdapter { actionUI ->
            when (actionUI) {
                is ActionUI.Click -> {
                    val bundle = bundleOf("popularMovie" to actionUI.movieItem)
                    this.findNavController()
                        .navigate(R.id.action_homeFragment_to_detailsFragment, bundle)

                }
                is ActionUI.Delete -> {}
                is ActionUI.Favorite -> {}
                is ActionUI.Share -> {}

            }

        }
        val layoutManagr = GridLayoutManager(this.context, 3)
        val headr = MovieLoadStateAdapter { movieAdapter.retry() }
        val footr = MovieLoadStateAdapter { movieAdapter.retry() }
        binding.recyclerView.apply {
//            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager = layoutManagr
            setHasFixedSize(true)

            //bind the LoadStateAdapter with the movieAdapter
            adapter = movieAdapter.withLoadStateHeaderAndFooter(
                header =headr,
                footer =footr
            )
        }


        layoutManagr.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0 && headr.itemCount > 0) {
                    // if it is the first position and we have a header,
                    3
                } else if (position == layoutManagr.itemCount - 1 && footr.itemCount > 0){
                    // if it is the last position and we have a footer
                    3
                } else {
                    1
                }
            }
        }

        movieAdapter2 = ViewPaggerMovieAdapter()
        setObservers()
        setViewPagger2()
        binding.swipe.setOnRefreshListener {
            movieViewModel.reload()
            movieAdapter.refresh()
        }
        lifecycleScope.launch {
            movieAdapter.loadStateFlow.collectLatest { loadStates ->
//                progressBar.isVisible = loadStates.refresh is LoadState.Loading
//                retry.isVisible = loadState.refresh !is LoadState.Loading
//                errorMsg.isVisible = loadState.refresh is LoadState.Error
            }
        }
        return binding.root
    }


    private fun setObservers() {
        movieViewModel.connectivityManag.isNetworkAvailable.observe(viewLifecycleOwner){
            Log.d("inter",it.toString())
        }
        viewLifecycleOwner.lifecycleScope.launch {
// select any page you want as your starting page
            val currentPageIndex = 2
            binding.viewPager.currentItem = currentPageIndex
            movieViewModel.popularMovies.collectLatest {
                movieAdapter.submitData(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.lifecycle.whenStarted {
//                repeatOnLifecycle(Lifecycle.State.STARTED) {


                movieViewModel.stateTwo.collect { uiStatedos ->
//                    uiStatedos.popularMovies?.let { movieAdapter.submitData(it) }
//Log.d("tag", uiStatedos.topRatedMovies?.size.toString())
                    if (!uiStatedos.loadingBar) {
                        binding.shimmer.stopShimmer()
                        binding.shimmer.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                    } else {
                        binding.shimmer.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }

                    movieAdapter2.submitList( uiStatedos.topRatedMovies)
//                    uiStatedos.topRatedMovies?.let {
//                        when (it) {
//                            is DataState.Data -> {
//
//                            }
//                            is DataState.Error -> {
//
//                            }
//                        }
//                    }
                    binding.swipe.isRefreshing=uiStatedos.loadingBar


                }



//            }
}

        }
    }

    //    private fun onQueryTextListener(adapter: MovieAdapter) =
//        object : SearchView.OnQueryTextListener {
//            override fun onQueryTextChange(newText: String?): Boolean {
//                // your text view here
//                //                                textView.setText(newText)
//                if (newText != null) {
//                    adapter.filter(newText)
//                }
//                return true
//            }
//
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                //                                textView.setText(query)
//
//                return true
//            }
//        }
//            binding.circleView.spin() // start spinning
//        mCircleView.stopSpinning(); // stops spinning. Spinner gets shorter until it disappears.
//            binding.circleView.mAnimationDuration=800
//            binding.circleView.setUnitVisible(false)
//            binding.circleView.setTextMode(TextMode.TEXT)
//            binding.circleView.setShowTextWhileSpinning(true)
//            binding.circleView.setText("Loading...")
//

    private fun setViewPagger2() {
        // MyRecyclerViewAdapter is an standard RecyclerView.Adapter :)
//    binding.viewPager.adapter = MyRecyclerViewAdapter()
        binding.viewPager.adapter = movieAdapter2
// You need to retain one page on each side so that the next and previous items are visible
        binding.viewPager.offscreenPageLimit = 1

// Add a PageTransformer that translates the next and previous items horizontally
// towards the center of the screen, which makes them visible
        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            // Next line scales the item's height. You can remove it if you don't want this effect
            page.scaleY = 1 - (0.25f * abs(position))
            // If you want a fading effect uncomment the next line:
            // page.alpha = 0.25f + (1 - abs(position))
        }

        binding.viewPager.setPageTransformer(pageTransformer)



// The ItemDecoration gives the current (centered) item horizontal margin so that
// it doesn't occupy the whole screen width. Without it the items overlap
        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.viewPager.addItemDecoration(itemDecoration)

    }

    private fun setUpViewPager() {


        //set the orientation of the viewpager using ViewPager2.orientation
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //select any page you want as your starting page
        val currentPageIndex = 1
        binding.viewPager.currentItem = currentPageIndex

        val pageMargin =
            resources.getDimensionPixelOffset(R.dimen.viewpager_current_item_horizontal_margin)
                .toFloat()
        val pageOffset =
            resources.getDimensionPixelOffset(R.dimen.viewpager_next_item_visible).toFloat()

        binding.viewPager.setPageTransformer { page, position ->
            val myOffset: Float = position * -(2 * pageOffset + pageMargin)
            if (position < -1) {
                page.translationX = -myOffset
            } else if (position <= 1) {
                val scaleFactor =
                    0.7f.coerceAtLeast(1 - kotlin.math.abs(position - 0.14285715f))
                page.translationX = myOffset
                page.scaleY = scaleFactor
                page.alpha = scaleFactor
            } else {
                page.alpha = 0F
                page.translationX = myOffset
            }
        }
    }


}
sealed class MyState2 {
    object Fetched : MyState2()
    object Error : MyState2()
}
inline fun <Result> Flow<NetworkStatus2>.map(
    crossinline onUnavailable: suspend () -> Result,
    crossinline onAvailable: suspend () -> Result,
): Flow<Result> = map { status ->
    when (status) {
        NetworkStatus2.Unavailable -> onUnavailable()
        NetworkStatus2.Available -> onAvailable()
    }
}
sealed class NetworkStatus2 {
    object Available : NetworkStatus2()
    object Unavailable : NetworkStatus2()
}

sealed interface ActionUI {
    class Click(val movieItem: MovieItem) : ActionUI
    class Favorite(val movieItem: MovieItem) : ActionUI
    class Share(val movieItem: MovieItem) : ActionUI
    class Delete(val movieItem: MovieItem) : ActionUI
}