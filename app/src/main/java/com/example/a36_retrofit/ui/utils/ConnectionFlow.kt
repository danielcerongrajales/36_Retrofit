package com.example.a36_retrofit.ui.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.*
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ConnectionFlow @Inject
constructor(@ApplicationContext context: Context) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val validNetworks: MutableSet<Network> = HashSet()
    @RequiresApi(Build.VERSION_CODES.M)
    val networkStatus = callbackFlow  {
        val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {


            override fun onUnavailable() {
                val lol=oo(null)
                channel.trySend(valor(false,lol ))
            }

            override fun onAvailable(network: Network) {
                val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                val hasInternetCapability = networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                if (hasInternetCapability == true) {
                    // check if this network actually has internet
                    CoroutineScope(Dispatchers.IO).launch {
                        val hasInternet = DoesNetworkHaveInternet.execute(network.socketFactory)
                        if(hasInternet){
                            withContext(Dispatchers.IO){
                                validNetworks.add(network)

                                    channel.trySend(valor(checkValidNetworks(), oo(network)))
                            }

                        }

                    }
                }
            }

            override fun onLost(network: Network) {
                validNetworks.remove(network)
                channel.trySend(valor(checkValidNetworks(), oo(network)))
            }
        }


        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(request, networkStatusCallback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkStatusCallback)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun oo(network: Network?) :Boolean{
        if (network==null)return false
        val linkProperties = connectivityManager.getLinkProperties(network)
        if (linkProperties != null) {
        if (linkProperties.httpProxy==null) return false
            linkProperties.httpProxy?.let {
                return it.toString().isNotEmpty()
            }
        }
        return false
    }

    private fun checkValidNetworks():Boolean {
        return (validNetworks.size > 0)
    }

}



data class valor(val internet:Boolean,val proxy:Boolean)

