package com.example.a36_retrofit.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.a36_retrofit.data.network.model.PopularMovies
import com.example.a36_retrofit.data.utils.DataState
import com.example.a36_retrofit.domain.homeDomain.model.MovieItem
import com.example.a36_retrofit.domain.homeDomain.model.toDomain

class MoviePagingSource(
    private val movieListResponse:DataState<PopularMovies>

) : PagingSource<Int, MovieItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {

        return try {
            val nextPage = params.key ?: 1
            when( movieListResponse ){
    is DataState.Data ->
        LoadResult.Page(
        data = movieListResponse.data!!.results!!.map { it.toDomain() },
        prevKey = if (nextPage == 1) null else nextPage - 1 ,
        nextKey = if (nextPage < movieListResponse.data.total_pages!!)
            movieListResponse.data.page?.plus(1) else null
    )
    is DataState.Error ->  LoadResult.Error(Throwable(movieListResponse.response.response?.message))
//    is DataState.Loading -> LoadResult.Invalid()
}
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        TODO("Not yet implemented")
    }


}
