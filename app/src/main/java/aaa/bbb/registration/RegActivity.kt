package aaa.bbb.registration

import aaa.bbb.registration.databinding.ActivityRegBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.InputType
import android.widget.Toast

class RegActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.eT2.hint = Html.fromHtml("Введите логин<sup>*</sup>")
        binding.eT3.hint = Html.fromHtml("Введите пароль<sup>*</sup>")
        binding.eT1.hint = "Введите код врача"

        binding.textView5.setOnClickListener {
            val intent = Intent(this, AuthActivity:: class.java )
            startActivity(intent)
            finish()
        }

        binding.iV1.setOnClickListener {
            binding.eT3.inputType = InputType.TYPE_CLASS_TEXT
        }

        binding.cardView4.setOnClickListener {
            if (binding.eT2.text.isEmpty() || binding.eT3.text.isEmpty()) {
                Toast.makeText(this, "Введите недостающие данные", Toast.LENGTH_SHORT).show()
            } else {
            val intent = Intent(this, HomeActivity:: class.java )
            startActivity(intent)
            finish() }
        }
    }
}