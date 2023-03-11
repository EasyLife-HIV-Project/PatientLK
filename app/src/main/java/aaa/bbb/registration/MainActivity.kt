package aaa.bbb.registration

import aaa.bbb.registration.databinding.ActivityMainBinding
import aaa.bbb.registration.patient.HomeActivity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var progressStatus = 0
    private var handler = Handler()
    private var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        Thread(Runnable {
            while (progressStatus < 100){

                progressStatus +=1

                Thread.sleep(50)

                handler.post {
                    binding.loadingPanel.progress = progressStatus
                    binding.textView2.text = "$progressStatus%"
                }
            }
        }).start()

        Handler().postDelayed({
            if (url.isEmpty()) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                url = "first"
                val pref: SharedPreferences =
                    getSharedPreferences("Go", MODE_PRIVATE)
                val editor = pref.edit()
                editor.putString("2", url).apply()
                finish()
            } else {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 5000)
    }

    private fun init(){
        val pref: SharedPreferences =
            getSharedPreferences("Go", MODE_PRIVATE)
        url = pref.getString("2", url)!!
    }
}