package com.example.a36_retrofit.ui.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.a36_retrofit.data.model.bad
import com.google.gson.Gson
import org.json.JSONObject

fun ImageView.load(url:String){
    if(url.isNotEmpty()){
        Glide.with(this.context).load("https://image.tmdb.org/t/p/w500$url").centerCrop().into(this);

    }

}
fun JSONObject.convert(): bad {
   return Gson().fromJson(this.toString(), bad::class.java)
}