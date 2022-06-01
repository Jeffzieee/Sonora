/* SONORA - SIGN UP Activity
   Language - Kotlin
   Code - jeffzieee
   Team - CodeRED. 2022
 */

package login

//Importing Depaendancies
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.codered.sonora.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class signupActivity : AppCompatActivity() {
    //Variable Declaration
    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var sonoradb : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding layout data
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        sonoradb = FirebaseFirestore.getInstance()

        //Signup Button Listener
        binding.btnSignup.setOnClickListener {
            val username = binding.nameText.text.toString()
            val phone = binding.phoneText.text.toString()
            val email = binding.emailText.text.toString()
            val pass = binding.passText.text.toString()
            val confirmPass = binding.confirmpassText.text.toString()

            if (username.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            saveuserData(sonoradb,firebaseAuth.currentUser!!.uid,username,phone,email)
                            finish()
                        } else {
                            val intent = Intent(this,retryActivity::class.java)
                            intent.putExtra("error",it.exception?.message)
                            startActivity(intent)

                        }
                    }
                } else {
                    Toast.makeText(this, "Passwords Mismatch. Please Re-Enter", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "One or more fields are empty. Please Re-enter", Toast.LENGTH_SHORT).show()

            }
        }

        binding.btnReturn.setOnClickListener{
            finish()
        }
    }

    //Custom Function to save UserData
    fun saveuserData(sonoradb: FirebaseFirestore, userId : String, username : String, phone : String, email : String){
        var userData : MutableMap<String,Any> = HashMap()
        userData["username"] = username
        userData["phone"] = phone
        userData["email"] = email

        sonoradb.collection("users").document(userId)
            .set(userData)
            .addOnSuccessListener {
                Toast.makeText(applicationContext,"UserData Added",Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener{
                val intent = Intent(this,retryActivity::class.java)
                intent.putExtra("error",it.message)
                startActivity(intent)
            }
    }

}