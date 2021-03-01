package code.challenge.mvvmsample.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import code.challenge.mvvmsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.lookupCity.setOnClickListener {
            val intent = Intent(this, ForecastActivity::class.java).apply {
                putExtra("city", binding.cityInputEditText.text.toString())
            }
            startActivity(intent)
        }
    }
}
