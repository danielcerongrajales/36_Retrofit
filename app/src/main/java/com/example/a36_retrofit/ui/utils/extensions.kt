package com.example.a36_retrofit.ui.utils

import android.graphics.Color
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import coil.clear
import coil.dispose
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.a36_retrofit.R
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

fun ImageView.loadCoiler(url: String?,pendingImage:LottieAnimationView, colorshimmer:String){
//    this.dispose()
    val drawable = ContextCompat.getDrawable(this.context, R.drawable.avd_dashboard)
(drawable as Animatable).start()
    val request = ImageRequest.Builder(this.context)
        .data("https://image.tmdb.org/t/p/w500$url")
        .crossfade(true)
        .error(drawable)
    .placeholder(shimmerDrawable(colorshimmer))
    .scale(Scale.FILL)
//        .transformations(CircleCropTransformation())
    .target(

        onStart = { placeholder ->
//                shimmerDrawable()
            this.setImageDrawable(placeholder)
            // Handle the placeholder drawable.
        },
        onSuccess = { result ->
            this.setImageDrawable(result)
            // Handle the successful result.
        },
        onError = { error ->
            this.setImageDrawable(error)
//                pendingImage.visibility = View.VISIBLE
//                pendingImage.scaleX = 0.5F
//                pendingImage.scaleY = 0.5F
//                pendingImage.setAnimation(R.raw.error)
//                pendingImage.playAnimation()
//                this.visibility = View.INVISIBLE
        }


    )
    .build()
this.context.imageLoader.enqueue(request)
}
fun ImageView.loadCoilSimple(url: String?, colorshimmer:String){

this.load("https://image.tmdb.org/t/p/w500$url") {
    crossfade(true)
    placeholder(shimmerDrawable(colorshimmer))

}
}

fun ImageView.loadSimple(url: String?, colorshimmer:String) {
Log.d("tag","$${url}")

if(!url.isNullOrEmpty()){
    Glide.with(this.context).load("https://image.tmdb.org/t/p/w500$url")
        .fitCenter()
        .placeholder(shimmerDrawable(colorshimmer))
//        .error(shimmerDrawable())
        .into(this)

}else{
    Glide.with(this.context).clear(this)
//                holder.imageView.setImageDrawable(specialDrawable);
}


}
fun ImageView.loadGlide(url: String?, pendingImage: LottieAnimationView, colorshimmer:String) {

/* val circularProgress = CircularProgressDrawable(this.context)
circularProgress.strokeWidth = 5f
circularProgress.centerRadius = 30f
circularProgress.start()*/



/*val drawable = ContextCompat.getDrawable(this.context, R.drawable.avd_dashboard)
val a = (drawable as Animatable).start()

val animated = AnimatedVectorDrawableCompat.create(this.context, R.drawable.avd_dashboard)
animated?.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
    override fun onAnimationEnd(drawable: Drawable?) {
        animated.start()
    }

})
animated?.start()*/




//    pendingImage.setVisibility(View.VISIBLE);
/*pendingImage.setAnimation(R.raw.animation)
pendingImage.scaleX = 0.5F
pendingImage.scaleY = 0.5F
pendingImage.playAnimation()*/

//    val  shimmerDrawable=shimmerDrawable()
//    shimmerDrawable.startShimmer()
    if (!url.isNullOrEmpty()) {

        val valor="https://image.tmdb.org/t/p/w500$url"
        Glide.with(this.context).load(valor)
//            .placeholder(drawable)
            .placeholder(shimmerDrawable(colorshimmer))
//            .error(shimmerDrawable())
            .addListener(imageLoadingListener(this,pendingImage,shimmerDrawable( colorshimmer)))
            .centerCrop().into(this)
//            shimmerDrawable.stopShimmer()
    }
//        else{
//            val valor="https://image.tmdb.org/t/p/w500"
//            Glide.with(this.context).load(valor)
////            .placeholder(drawable)
//                .placeholder(shimmerDrawable())
////                .error(shimmerDrawable())
//                .addListener(imageLoadingListener(this,pendingImage,shimmerDrawable()))
//                .centerCrop().into(this)
////            this.visibility = View.INVISIBLE
//        }


}


fun shimmerDrawable(color:String): ShimmerDrawable {
val shimmer=Shimmer.ColorHighlightBuilder()
    .setBaseColor(Color.parseColor(color))
    .setBaseAlpha(1F)
    .setHighlightColor(Color.parseColor("#E7E7E7"))
    .setHighlightAlpha(1F)
    .setDropoff(100F)
    .build()
val shimmerDrawable = ShimmerDrawable()
  shimmerDrawable.setShimmer(shimmer)

return shimmerDrawable
}


fun imageLoadingListener(image:ImageView, pendingImage: LottieAnimationView, shimmerDrawable:ShimmerDrawable): RequestListener<Drawable?> {
return object : RequestListener<Drawable?> {
    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable?>?,
        isFirstResource: Boolean
    ): Boolean {
        pendingImage.visibility = View.VISIBLE
        pendingImage.scaleX = 0.5F
        pendingImage.scaleY = 0.5F
        pendingImage.setAnimation(R.raw.error)
        pendingImage.playAnimation()
//            shimmerDrawable.stopShimmer()
//            image.visibility = View.GONE
        return false
    }

    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: Target<Drawable?>?,
        dataSource: com.bumptech.glide.load.DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        shimmerDrawable.stopShimmer()
        pendingImage.pauseAnimation()
        pendingImage.visibility = View.GONE

        return false//let Glide handle everything else
    }

}
}

//fun JSONObject.convert(): bad {
//    return Gson().fromJson(this.toString(), bad::class.java)
//}