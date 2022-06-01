/* SONORA - RETRY Activity
   Language - Kotlin
   Code - jeffzieee
   Team - CodeRED. 2022
 */

package home

//importing Dependancies
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codered.sonora.databinding.ActivityHomeBinding


class homeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}