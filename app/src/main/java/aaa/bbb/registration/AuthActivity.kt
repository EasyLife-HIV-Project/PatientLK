package aaa.bbb.registration

import aaa.bbb.registration.databinding.ActivityAuthBinding
import aaa.bbb.registration.parent.ParentHomeActivity
import aaa.bbb.registration.patient.HomeActivity
import aaa.bbb.registration.retrofit.PatientApi
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.InputType
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import java.util.*

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    @RequiresApi(Build.VERSION_CODES.O)
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

        binding.tV8.setOnClickListener {
            val intent = Intent(this, AnswerActivity:: class.java )
            intent.putExtra("Remember", "forget")
            startActivity(intent)
            finish()
        }

        binding.cardView7.setOnClickListener {
            if (binding.eT5.text!!.isEmpty()) {
                Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show()
            } else {
                getUser()
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getUser() {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://83.222.11.163/")
            .build()

        val service = retrofit.create(PatientApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getUser()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string()
                        )
                    )

                    Log.d("Pretty Printed JSON :", response.toString())
                    if (binding.checkBox2.isChecked) {
                        val intent = Intent(this@AuthActivity, ParentHomeActivity:: class.java )
                        startActivity(intent)
                        finish()
                    } else {
                        val intent = Intent(this@AuthActivity, HomeActivity:: class.java )
                        startActivity(intent)
                        finish()
                    }

                } else {
                    Toast.makeText(this@AuthActivity, "Введите корректные данные", Toast.LENGTH_SHORT).show()
                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }
}