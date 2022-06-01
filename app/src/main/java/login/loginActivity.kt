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
import android.widget.Toast
import com.codered.sonora.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class loginActivity : AppCompatActivity() {
    //variable declaratiopn
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding layout data
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        //LOG IN Button Listener
        binding.btnLogin.setOnClickListener{
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
        }

        //Intenting to Sign Up
        binding.signupTextView.setOnClickListener{
            val intent = Intent(this,signupActivity::class.java)
            startActivity(intent)
        }



    }
}