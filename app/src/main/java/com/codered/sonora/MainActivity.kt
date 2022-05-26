package com.codered.sonora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.codered.sonora.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding= ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            firebaseAuth = FirebaseAuth.getInstance()

            binding.btnLogin.setOnClickListener{
                val email = binding.emailText.text.toString()
                val pass = binding.passText.text.toString()

                if(email.isNotEmpty() && pass.isNotEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent = Intent(this,WelcomeActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(applicationContext,it.exception.toString(),
                                Toast.LENGTH_LONG).show()
                        }
                    }
                }
                else{
                    Toast.makeText(applicationContext,"One or more fields are empty. Please Re-enter",
                        Toast.LENGTH_LONG).show()
                }
            }
            binding.signupTextView.setOnClickListener{
                val intent = Intent(this,signupActivity::class.java)
                startActivity(intent)
            }
        }
    }