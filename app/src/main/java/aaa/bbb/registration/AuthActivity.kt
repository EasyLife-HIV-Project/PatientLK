package aaa.bbb.registration

import aaa.bbb.registration.databinding.ActivityAuthBinding
import aaa.bbb.registration.parent.ParentHomeActivity
import aaa.bbb.registration.patient.HomeActivity
import aaa.bbb.registration.retrofit.PatientApi
import android.content.Intent
import android.content.SharedPreferences
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
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Retrofit
import java.util.*

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // <item
        //        android:id="@+id/message"
        //        android:title="Чат"
        //        android:icon="@drawable/ic_round_message_24"/>

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
            if (binding.eT5.text.toString().length < 2) {
                Toast.makeText(this, "Количество символов не должно быть меньше 8", Toast.LENGTH_SHORT).show()
            } else {
                postUser()
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun postUser() {

        val service = PatientApi.create()

        val jsonObject = JSONObject()
        val login = binding.eT4.text
        val password = binding.eT5.text
        val cred = Base64.getEncoder().encodeToString("$login:$password".toByteArray())
        jsonObject.put("credentials", cred.toString())

        val jsonObjectString = jsonObject.toString()
        Log.d("stringpost", jsonObjectString)

        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.postUser(requestBody)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string()
                        )
                    )
                    Log.d("Pretty JSONPOST:", prettyJson)
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

                    Log.e("RETROFIT_ERROR2", response.code().toString())

                }
            }
        }
    }
}