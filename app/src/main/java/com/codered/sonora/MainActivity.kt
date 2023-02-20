/* SONORA - MAIN Activity
   Language - Kotlin
   Code - jeffzieee
   Team - CodeRED. 2022
 */

package com.codered.sonora

//Importing Dependancies
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.content.Intent
import com.codered.sonora.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    //variable declaratiopn


    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

        Handler(Looper.getMainLooper()!!).postDelayed({
            val intent = Intent(this,login.loginActivity::class.java)
            startActivity(intent)
            finish()
        },1500)

        }
    }