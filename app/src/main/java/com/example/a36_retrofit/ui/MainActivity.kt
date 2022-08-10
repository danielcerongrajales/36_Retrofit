package com.example.a36_retrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.a36_retrofit.databinding.ActivityMainBinding
import com.example.a36_retrofit.ui.utils.ConnectivityManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)


    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        var change=true

        splashScreen.setKeepOnScreenCondition {
            change
        }
     connectivityManager.isNetworkAvailable.observe(this){
            if(it){
               change=false
                binding.textView9.visibility=GONE

            }else {
                binding.textView9.text="no Internet"
                binding.textView9.visibility=VISIBLE

            }
        }

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}