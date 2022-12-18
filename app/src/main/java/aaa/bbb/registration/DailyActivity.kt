package aaa.bbb.registration

import aaa.bbb.registration.databinding.ActivityDailyBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DailyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDailyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}