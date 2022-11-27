package aaa.bbb.registration

import aaa.bbb.registration.databinding.ActivityAuthBinding
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.InputType
import android.widget.Toast

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.eT4.hint = "Введите логин"
        binding.eT5.hint = Html.fromHtml("Введите пароль<sup>*</sup>")

        binding.textView9.setOnClickListener {
            val intent = Intent(this, RegActivity:: class.java )
            startActivity(intent)
            finish()
        }

        binding.iV2.setOnClickListener {
            binding.eT5.inputType = InputType.TYPE_CLASS_TEXT
        }

        binding.cardView7.setOnClickListener {
            if (binding.eT5.text.isEmpty()) {
                Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, HomeActivity:: class.java )
                startActivity(intent)
                finish()
            }
        }
    }
}