package login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.codered.sonora.databinding.ActivitySuccessBinding

class successActivity : AppCompatActivity() {
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