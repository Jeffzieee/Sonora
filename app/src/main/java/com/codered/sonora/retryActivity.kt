package com.codered.sonora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.codered.sonora.databinding.ActivityRetryBinding
import kotlin.system.exitProcess

class retryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRetryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.exceptionText.setText(intent.extras?.getString("error"))
        binding.btnRetry.setOnClickListener{
            finish()
        }
    }
}