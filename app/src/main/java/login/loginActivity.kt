/* SONORA - LOG IN Activity
   Language - Kotlin
   Code - jeffzieee
   Team - CodeRED. 2022
 */

package login

//Importing Dependancies
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import com.codered.sonora.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

class loginActivity : AppCompatActivity() {
    //variable declaratiopn
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo : BiometricPrompt.PromptInfo

    var currentTime : Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding layout data
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = BiometricPrompt(this, executor,
        object : BiometricPrompt.AuthenticationCallback(){

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)

                Toast.makeText(applicationContext," Error " + errString,
                    Toast.LENGTH_LONG).show()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)

                Toast.makeText(applicationContext,"Biometric Authentication Successful",
                    Toast.LENGTH_LONG).show()
                val intent = Intent(this@loginActivity,home.homeActivity::class.java)
                startActivity(intent)

            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()

                Toast.makeText(applicationContext,"Biometric Authentication Failed",
                    Toast.LENGTH_LONG).show()
            }

        })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Login for My App")
            .setSubtitle("Use your Biometric Login Credentials")
            .setNegativeButtonText("Cancel")
            .build()

        binding.txtBio.setOnClickListener{
            biometricPrompt.authenticate(promptInfo)
        }


        //Firebase
        firebaseAuth = FirebaseAuth.getInstance()


        //LOG IN Button Listener
        binding.btnLogin.setOnClickListener{
               if(SystemClock.elapsedRealtime()-currentTime<1000)
                   return@setOnClickListener

            val email = binding.emailText.text.toString()
            val pass = binding.passText.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent = Intent(this,home.homeActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        val intent = Intent(this,retryActivity::class.java)
                        intent.putExtra("error",it.exception?.message)
                        startActivity(intent)
                    }
                }
            }
            else{
                Toast.makeText(applicationContext,"One or more fields are empty. Please Re-enter",
                    Toast.LENGTH_LONG).show()
            }
            currentTime  = SystemClock.elapsedRealtime()
        }

        //Biometric Authentication


        //Intenting to Sign Up
        binding.signupTextView.setOnClickListener{
            if(SystemClock.elapsedRealtime()-currentTime<1000)
                return@setOnClickListener

            val intent = Intent(this,signupActivity::class.java)
            startActivity(intent)

            currentTime  = SystemClock.elapsedRealtime()
        }



    }
}