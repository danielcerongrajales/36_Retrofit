package com.example.a36_retrofit

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks2
import android.content.res.Configuration
import android.os.Bundle

class AppLifecycleHandler(private val lifeCycleDelegate: LifeCycleDelegate) :
    Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {

     private var appInForeground = false
    override fun onActivityResumed(p0: Activity) {
        if (!appInForeground) {
            appInForeground = true
            lifeCycleDelegate.onAppForegrounded()
        }
    }
    override fun onActivityPaused(activity: Activity) = Unit
    override fun onActivityStopped(activity: Activity) = Unit
    override  fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit
    override fun onActivityDestroyed(activity: Activity) {
        lifeCycleDelegate.destroyed()
    }
    override fun onTrimMemory(level: Int) {
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            appInForeground = false
            lifeCycleDelegate.onAppBackgrounded()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) = Unit
     override fun onLowMemory()= Unit

     override fun onActivityCreated(p0: Activity, p1: Bundle?) = Unit

    override fun onActivityStarted(p0: Activity) {
        lifeCycleDelegate.onActivityStarted()
    }


}

interface LifeCycleDelegate {
    fun onAppBackgrounded()
    fun onAppForegrounded()
    fun onActivityStarted()
    fun onResumeCalled()
    fun destroyed()
}