package aaa.bbb.registration.fragments

import aaa.bbb.registration.DailyActivity
import aaa.bbb.registration.R
import aaa.bbb.registration.databinding.FragmentProfileBinding
import aaa.bbb.registration.retrofit.PatientApi
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ProfileFragment: Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private var question = 0
    private var answer = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        binding.textView9.setText(answer)

        val questions = resources.getStringArray(R.array.Questions)
        val spinner = binding.spinner
        val adapter = ArrayAdapter(
            this.requireContext(),
            android.R.layout.simple_spinner_item, questions
        )
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?, position: Int, id: Long
            ) {
                Log.d("No", "nothing")
                val pref: SharedPreferences =
                    requireActivity().getSharedPreferences(
                        "Control",
                        AppCompatActivity.MODE_PRIVATE
                    )
                val editor = pref.edit()
                editor.putInt("2", question).apply()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("No", "nothing")
            }
        }
        binding.textView16.setOnClickListener {
                answer = binding.textView9.text.toString()
                val pref: SharedPreferences =
                    requireActivity().getSharedPreferences(
                        "Control",
                        AppCompatActivity.MODE_PRIVATE
                    )
                val editor = pref.edit()
                editor.putString("1", answer).apply()
            Toast.makeText(this@ProfileFragment.requireContext(), "Вопрос успешно сохранен", Toast.LENGTH_SHORT).show()
        }
        binding.textView19.setOnClickListener {
            generateParent()
        }
        binding.iV15.setOnClickListener {
            val intent = Intent(this@ProfileFragment.requireContext(), DailyActivity:: class.java)
            startActivity(intent)
        }
        tapCat()
    }

    private fun init(){
        val pref: SharedPreferences =
            requireActivity().getSharedPreferences("Control", AppCompatActivity.MODE_PRIVATE)
        answer = pref.getString("1", answer)!!
        question = pref.getInt("2", question)
    }

    private fun tapCat(){
        val animationBounce = AnimationUtils.loadAnimation(this.requireActivity(), R.anim.bounce)
        binding.cardView.setOnClickListener {
            binding.catview.startAnimation(animationBounce)
        }
        binding.cardView1.setOnClickListener {
            binding.cardView.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardfonepink)
            binding.cardView1.foreground = ContextCompat.getDrawable(requireContext(), R.drawable.cardfonepink)
            binding.cardView2.foreground = ContextCompat.getDrawable(requireContext(), R.color.brown)
            binding.cardView3.foreground = ContextCompat.getDrawable(requireContext(), R.color.orange)
            binding.cardView4.foreground = ContextCompat.getDrawable(requireContext(), R.color.grey1)
        }
        binding.cardView2.setOnClickListener {
            binding.cardView.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardfonebrown)
            binding.cardView1.foreground = ContextCompat.getDrawable(requireContext(), R.color.pink)
            binding.cardView2.foreground = ContextCompat.getDrawable(requireContext(), R.drawable.cardfonebrown)
            binding.cardView3.foreground = ContextCompat.getDrawable(requireContext(), R.color.orange)
            binding.cardView4.foreground = ContextCompat.getDrawable(requireContext(), R.color.grey1)
        }
        binding.cardView3.setOnClickListener {
            binding.cardView.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardfoneorange)
            binding.cardView1.foreground = ContextCompat.getDrawable(requireContext(), R.color.pink)
            binding.cardView2.foreground = ContextCompat.getDrawable(requireContext(), R.color.brown)
            binding.cardView3.foreground = ContextCompat.getDrawable(requireContext(), R.drawable.cardfoneorange)
            binding.cardView4.foreground = ContextCompat.getDrawable(requireContext(), R.color.grey1)
        }
        binding.cardView4.setOnClickListener {
            binding.cardView.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardfonegrey)
            binding.cardView1.foreground = ContextCompat.getDrawable(requireContext(), R.color.pink)
            binding.cardView2.foreground = ContextCompat.getDrawable(requireContext(), R.color.brown)
            binding.cardView3.foreground = ContextCompat.getDrawable(requireContext(), R.color.orange)
            binding.cardView4.foreground = ContextCompat.getDrawable(requireContext(), R.drawable.cardfonegrey)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun generateParent(){

        val service = PatientApi.create()

            CoroutineScope(Dispatchers.IO).launch {
                val response = service.createParent()

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {

                        val gson = GsonBuilder().setPrettyPrinting().create()
                        val prettyJson = gson.toJson(
                            JsonParser.parseString(
                                response.body()
                                    ?.string()
                            )
                        )
                        Log.d("Pretty Printed JSON :", prettyJson.toString())
                        binding.textView20.text = prettyJson
                    } else {
                        Log.e("RETROFIT_ERROR", response.code().toString())

                    }
                }
            }
        }
    }