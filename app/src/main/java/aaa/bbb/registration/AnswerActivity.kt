package aaa.bbb.registration

import aaa.bbb.registration.databinding.ActivityAnswerBinding
import aaa.bbb.registration.parent.ParentHomeActivity
import aaa.bbb.registration.patient.HomeActivity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class AnswerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnswerBinding
    private var question = ""
    private var answer = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        binding.eT7.setText(question)
        binding.eT6.setText(answer)
        val remember = intent.getStringExtra("Remember").toString()
        if (remember == "pastePar" || remember == "pastePat") {
            users()
        } else {
            forgetUsers()
        }

    }

    private fun init(){
        val pref: SharedPreferences =
            getSharedPreferences("Control", MODE_PRIVATE)
        answer = pref.getString("1", answer)!!
        question = pref.getString("2", question)!!
    }

    private fun users() {
        val remember = intent.getStringExtra("Remember").toString()
        binding.tV11.setOnClickListener {
            if (remember == "pastePar") {
                val intent = Intent(this, ParentHomeActivity::class.java)
                startActivity(intent)
                finish()
            } else if (remember == "pastePat") {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        binding.cardView7.setOnClickListener {
            if (binding.eT6.text.toString().isEmpty() || binding.eT7.text.toString().isEmpty()) {
                Toast.makeText(this, "Введите контрольный вопрос", Toast.LENGTH_SHORT).show()
            } else {
                answer = binding.eT6.text.toString()
                question = binding.eT7.text.toString()
                val pref: SharedPreferences =
                    getSharedPreferences("Control", MODE_PRIVATE)
                val editor = pref.edit()
                editor.putString("1", answer).apply()
                editor.putString("2", question).apply()
                if (remember == "pastePar") {
                    val intent = Intent(this, ParentHomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } else if (remember == "pastePat") {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun forgetUsers(){
        binding.tV11.visibility = View.INVISIBLE
        binding.tv9.text = "Ввод"
        binding.cardView7.setOnClickListener {
            if (binding.eT6.text.toString().length < 8 || binding.eT7.text.toString().length < 8) {
                Toast.makeText(this, "Количество символов не должно быть меньше 8", Toast.LENGTH_SHORT).show()
            } else {
                val pref: SharedPreferences =
                    getSharedPreferences("Control", MODE_PRIVATE)
                if (binding.eT6.text.toString() == pref.getString("1", answer)) {
                    Toast.makeText(this, "Успешно", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Попробуйте еще раз", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
