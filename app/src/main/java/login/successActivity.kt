/* SONORA - SUCCESS Activity
   Language - Kotlin
   Code - jeffzieee
   Team - CodeRED. 2022
 */

package login

//Importing Dependancies
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codered.sonora.databinding.ActivitySuccessBinding

class successActivity : AppCompatActivity() {
    //Declaring variables
    private lateinit var binding : ActivitySuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinue.setOnClickListener{
            finish()
        }

    }
}