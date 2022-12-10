package aaa.bbb.registration

import aaa.bbb.registration.databinding.ActivityRegBinding
import aaa.bbb.registration.parent.ParentHomeActivity
import aaa.bbb.registration.patient.HomeActivity
import aaa.bbb.registration.retrofit.PatientApi
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.InputType
import java.util.Base64
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

class RegActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegBinding

    @RequiresApi(Build.VERSION_CODES.O)
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
                if (binding.checkBox1.isChecked) {
                    postParent()
                    val intent = Intent(this, ParentHomeActivity:: class.java )
                    startActivity(intent)
                    finish()
                } else {
                    postPatient()
                    val intent = Intent(this, HomeActivity:: class.java )
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun postPatient() {
        patientReg()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun postParent() {
        parentReg()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun patientReg() {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://83.222.11.163/")
            .build()

        val service = retrofit.create(PatientApi::class.java)

        val jsonObject = JSONObject()
        val login = binding.eT2.text
        val password = binding.eT3.text
        val cred = Base64.getEncoder().encodeToString("$login:$password".toByteArray())
        jsonObject.put("credentials", cred.toString())
        jsonObject.put("code", binding.eT1.text)

        val jsonObjectString = jsonObject.toString()
        Log.d("string", jsonObjectString)

        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.createPatient(requestBody)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string()
                        )
                    )
                    Log.d("Pretty Printed JSON :", prettyJson)

                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun parentReg() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://83.222.11.163/")
            .build()

        val service = retrofit.create(PatientApi::class.java)

        val jsonObject = JSONObject()
        val login = binding.eT2.text
        val password = binding.eT3.text
        val cred = Base64.getEncoder().encodeToString("$login:$password".toByteArray())
        jsonObject.put("credentials", cred.toString())
        jsonObject.put("code", binding.eT1.text)

        val jsonObjectString = jsonObject.toString()

        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.createParent(requestBody)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string()
                        )
                    )
                    Log.d("Pretty Printed JSON2 :", prettyJson)

                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }
}