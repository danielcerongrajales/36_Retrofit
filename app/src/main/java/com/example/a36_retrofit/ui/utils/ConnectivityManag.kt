package com.example.a36_retrofit.ui.utils

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityManag
@Inject
constructor(
    application: Application,
) {
    private val connectionLiveData = ConnectionLiveData(application)

    private val connectionLiveDatas= ConnectionFlow(application)
    private val uiScope= CoroutineScope(Dispatchers.Main)
    private val uiScope2= CoroutineScope(Dispatchers.Main)

    val isNetworkAvailable = MutableLiveData(false)

    val isNetworkAvailable2 = MutableLiveData<valor>()
    @RequiresApi(Build.VERSION_CODES.M)
    fun registerConnectionObserver(){
        uiScope.launch{
            connectionLiveData.observeForever  { isConnected ->
                isConnected?.let { isNetworkAvailable.value = it }
            }}
            uiScope2.launch{
                connectionLiveDatas.networkStatus.asLiveData().observeForever {
                            isConnected ->
                        isConnected?.let {

                            isNetworkAvailable2.value = it
                            Log.d("hola","${ it.internet}rr${it.proxy}")
                        }

                    }
                }

        }


    fun unregisterConnectionObserver(){
        isNetworkAvailable2.value = valor(internet = false, proxy = false)
        uiScope.cancel()
        uiScope2.cancel()
    }
}