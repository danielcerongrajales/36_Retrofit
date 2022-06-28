package com.example.a36_retrofit.data.network

import com.example.a36_retrofit.data.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import javax.inject.Inject

class MovieService @Inject constructor(private val retrofit:ApiClient,private var pop:PopularMovies){




    suspend fun getPopularMovies()= retrofit.getMoviePopular()
//return try{
//        var respu= withContext(Dispatchers.IO) { retrofit.getMoviePopular() }

//        val jObjError = JSONObject(respu.errorBody()?.string() ?:"" )
//        val user: bad =jObjError.convert()
//
//        when(respu.code()){
//            401 -> RespuestaModel.HttpErrors.ResourceForbidden(user.status_message!!)
//            404 -> RespuestaModel.HttpErrors.ResourceNotFound(respu.message())
//            500 -> RespuestaModel.HttpErrors.InternalServerError(respu.message())
//            502 -> RespuestaModel.HttpErrors.BadGateWay(respu.message())
//            301 -> RespuestaModel.HttpErrors.ResourceRemoved(respu.message())
//            302 -> RespuestaModel.HttpErrors.RemovedResourceFound(respu.message())
//            else -> RespuestaModel.Failure(respu.message())
//
//    }
//    }



//    }




      suspend fun getKeywordFromQuery(query:String): KeywordByQuery = withContext(Dispatchers.IO) {

          retrofit.getKeywordFromQuery(query).body()!!
     }


    suspend fun getMovieByKeyword(key:String): MovieByKeyword = withContext(Dispatchers.IO) {

        retrofit.getMovieByKeyword(key).body()!!
    }


    suspend fun getMovieById(id:Int): MovieById = withContext(Dispatchers.IO) {

    retrofit.getMovieById(id).body()!!
}

    suspend fun getCollectionMovieById(id:String): CollectionById = withContext(Dispatchers.IO) {

        retrofit.getCollectionMovieById(id).body()!!
    }

    suspend fun getMovieKeywordsById(id:String): KeywordsMovieById = withContext(Dispatchers.IO) {

        retrofit.getMovieKeywordsById(id).body()!!
    }
}