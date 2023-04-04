package com.example.a36_retrofit

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.a36_retrofit.ui.utils.ConnectivityManag
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class RetrofitApp : Application(), LifeCycleDelegate {
    @Inject
    lateinit var connectivityManag: ConnectivityManag

    private val lifecycleHandler = AppLifecycleHandler(this)
    override fun onCreate() {
        super.onCreate()
        registerLifecycleHandler(lifecycleHandler)
    }

    override fun onAppBackgrounded() {
        Log.d("Awww", "App in background")
    }

    override fun onAppForegrounded() {
        Log.d("Yeeey", "App in foreground")
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityStarted() {
        connectivityManag.registerConnectionObserver()
    }

    override fun onResumeCalled() {
    }

    override fun destroyed() {
        connectivityManag.unregisterConnectionObserver()
    }

    private fun registerLifecycleHandler(lifeCycleHandler: AppLifecycleHandler) {
        registerActivityLifecycleCallbacks(lifeCycleHandler)
        registerComponentCallbacks(lifeCycleHandler)
    }





}
