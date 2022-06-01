/* SONORA - RETRY Activity
   Language - Kotlin
   Code - jeffzieee
   Team - CodeRED. 2022
 */

package login

//Importing Depaendancies
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.codered.sonora.databinding.ActivityRetryBinding
import kotlin.system.exitProcess

class retryActivity : AppCompatActivity() {
    //Variable Declaration
    private lateinit var binding: ActivityRetryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding layout data
        binding = ActivityRetryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.exceptionText.setText(intent.extras?.getString("error"))

        //Retry Button Listener
        binding.btnRetry.setOnClickListener{
            finish()
        }
    }
}