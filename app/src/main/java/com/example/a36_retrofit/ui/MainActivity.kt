package com.example.a36_retrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.*
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.a36_retrofit.databinding.ActivityMainBinding
import com.example.a36_retrofit.ui.utils.ConnectivityManag
import com.example.a36_retrofit.ui.utils.EmulatorDetector
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var connectivityManag: ConnectivityManag

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        var change=true

        splashScreen.setKeepOnScreenCondition {
           Log.d("yel","sies ${EmulatorDetector.isEmulator(this)}")
            change
        }
     connectivityManag.isNetworkAvailable2.observe(this){
            if(it.internet){
               change=false
                binding.textView9.visibility=GONE
                if(it.proxy){
                    Toast.makeText(this, "no se admite proxy ", Toast.LENGTH_SHORT).show()

                }

            }else {
                binding.textView9.text="no Internet"
                binding.textView9.visibility=VISIBLE

            }

        }
        binding=ActivityMainBinding.inflate(layoutInflater)




        setContentView(binding.root)


    }
}
