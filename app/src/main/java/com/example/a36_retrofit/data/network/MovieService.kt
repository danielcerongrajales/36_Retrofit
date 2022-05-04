package com.example.a36_retrofit.data.network

//import com.google.gson.Gson
import android.util.Log
import com.example.a36_retrofit.core.Respuesta
import com.example.a36_retrofit.data.model.*
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject
import com.example.a36_retrofit.ui.ext.convert

class MovieService @Inject constructor(private val retrofit:ApiClient,private var pop:PopularMovies){



    suspend fun getPopularMovies():Respuesta {
        var a:Respuesta=Respuesta.Loading()
//return try{


   var respu= withContext(Dispatchers.IO) {
            retrofit.getMoviePopular()
        }
    if (respu.isSuccessful){
        a= respu.body()?.let { Respuesta.Success(it) }!!

    }else{
        val jObjError = JSONObject(respu.errorBody()?.string() ?:"" )
        val user: bad =jObjError.convert()

        when(respu.code()){
            401 -> Respuesta.HttpErrors.ResourceForbidden(user.status_message!!)
            404 -> Respuesta.HttpErrors.ResourceNotFound(respu.message())
            500 -> Respuesta.HttpErrors.InternalServerError(respu.message())
            502 -> Respuesta.HttpErrors.BadGateWay(respu.message())
            301 -> Respuesta.HttpErrors.ResourceRemoved(respu.message())
            302 -> Respuesta.HttpErrors.RemovedResourceFound(respu.message())
            else -> Respuesta.Failure(respu.message())
//
    }
    }
//}catch (error : IOException){
//    Respuesta.Error(error.message!!)
//}
        return    a

    }




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