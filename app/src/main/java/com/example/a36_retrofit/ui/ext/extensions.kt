package com.example.a36_retrofit.ui.ext

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.a36_retrofit.R
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable


fun ImageView.load(url: String, pendingImage: LottieAnimationView) {

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




   /* pendingImage.setVisibility(View.VISIBLE);
    pendingImage.setAnimation(R.raw.animation)
    pendingImage.scaleX = 0.5F
    pendingImage.scaleY = 0.5F
    pendingImage.playAnimation()*/
    if (url.isNotEmpty()) {
        val shimmerDrawable=ShimmerDrawable()
        shimmerDrawable.setShimmer(shimer())
        Glide.with(this.context).load("https://image.tmdb.org/t/p/w500$url")
//            .placeholder(drawable)
            .placeholder(drawable)

//            .error(animated)
            .addListener(imageLoadingListener(pendingImage))
            .centerCrop().into(this)


    }

}

fun shimer(): Shimmer {
    return Shimmer.ColorHighlightBuilder()
        .setBaseColor(Color.parseColor("#f3f3f3"))
        .setBaseAlpha(1F)
        .setHighlightColor(Color.parseColor("#E7E7E7"))
        .setHighlightAlpha(1F)
        .setDropoff(50F)
        .build()
}

fun imageLoadingListener(pendingImage: LottieAnimationView): RequestListener<Drawable?> {
    return object : RequestListener<Drawable?> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable?>?,
            isFirstResource: Boolean
        ): Boolean {
//            pendingImage.pauseAnimation();
//            pendingImage.scaleX = 1F
//            pendingImage.scaleY = 1F
            pendingImage.setAnimation(R.raw.error)
            pendingImage.playAnimation()
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable?>?,
            dataSource: com.bumptech.glide.load.DataSource?,
            isFirstResource: Boolean
        ): Boolean {
//            pendingImage.pauseAnimation();
//            pendingImage.setVisibility(View.GONE);
            return false//let Glide handle everything else
        }

    }
}

//fun JSONObject.convert(): bad {
//    return Gson().fromJson(this.toString(), bad::class.java)
//}