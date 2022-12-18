package aaa.bbb.registration.fragments

import aaa.bbb.registration.AlarmActivity
import aaa.bbb.registration.MainActivity
import aaa.bbb.registration.R
import aaa.bbb.registration.databinding.FragmentMaterialBinding
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.AlarmManager.AlarmClockInfo
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*


@Suppress("DEPRECATION")
class CalendarFragment : Fragment() {
    private lateinit var binding: FragmentMaterialBinding
    private val adapter = CalendarAdapter()
    private val listName = listOf("Таблетка 1", "Таблетка 2", "Таблетка 3", "Таблетка 4", "Таблетка 5", "Таблетка 6")
    private val listTime = listOf("11:00", "13:00", "14:00", "16:00", "18:00", "21:00")
    private val listTabl = listOf("1 раз в день после еды", "1 раз через 2 часа после завтрака", "1 раз в день перед обедом", "1 раз через 2 часа после завтрака", "1 раз в день перед ужином", "1 раз в день перед сном")
    private var index = 0
    var dateList = ArrayList<CardDate>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMaterialBinding.inflate(layoutInflater)
        return binding.root
    }


    @SuppressLint("SuspiciousIndentation", "UseCompatLoadingForColorStateLists", "ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sdf = SimpleDateFormat("HH:mm", Locale("ru"))

        binding.iV1.setOnClickListener {
            val materialTimePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Выберите время для будильника")
                .build()

        materialTimePicker.addOnPositiveButtonClickListener{
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            calendar.set(Calendar.MINUTE, materialTimePicker.minute)
            calendar.set(Calendar.HOUR_OF_DAY, materialTimePicker.hour)

            val alarmManager: AlarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val alarmClockInfo = AlarmClockInfo(calendar.timeInMillis, getAlarmInfoPendingIntent())

            alarmManager.setAlarmClock(alarmClockInfo, getAlarmActionPendingIntent())
            Toast.makeText(this.requireContext(), "Будильник установлен на " + sdf.format(calendar.time), Toast.LENGTH_SHORT).show();
        }

            materialTimePicker.show(parentFragmentManager, "tag_picker")

    }
        binding.cardM1.setOnClickListener {
            binding.cardM1.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardblueselector)
            binding.cardM2.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM3.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM4.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM5.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM6.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM7.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
        }
        binding.cardM2.setOnClickListener {
            binding.cardM1.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM2.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardblueselector)
            binding.cardM3.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM4.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM5.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM6.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM7.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
        }
        binding.cardM3.setOnClickListener {
            binding.cardM1.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM2.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM3.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardblueselector)
            binding.cardM4.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM5.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM6.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM7.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
        }
        binding.cardM4.setOnClickListener {
            binding.cardM1.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM2.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM3.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM4.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardblueselector)
            binding.cardM5.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM6.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM7.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
        }
        binding.cardM5.setOnClickListener {
            binding.cardM1.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM2.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM3.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM4.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM5.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardblueselector)
            binding.cardM6.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM7.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
        }
        binding.cardM6.setOnClickListener {
            binding.cardM1.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM2.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM3.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM4.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM5.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM6.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardblueselector)
            binding.cardM7.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
        }
        binding.cardM7.setOnClickListener {
            binding.cardM1.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM2.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM3.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM4.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM5.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM6.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardgreyselector)
            binding.cardM7.background = ContextCompat.getDrawable(requireContext(), R.drawable.cardblueselector)
        }
        binding.search.setOnClickListener {
            binding.eTsearch.requestFocus()
        }
        init()
        filter()
    }

        @SuppressLint("UnspecifiedImmutableFlag")
        private fun getAlarmInfoPendingIntent(): PendingIntent? {
            val alarmInfoIntent = Intent(this.requireContext(), MainActivity::class.java)
            alarmInfoIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            return PendingIntent.getActivity(
                this.requireContext(),
                0,
                alarmInfoIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        @SuppressLint("UnspecifiedImmutableFlag")
        private fun getAlarmActionPendingIntent(): PendingIntent? {
            val intent = Intent(this.requireContext(), AlarmActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            return PendingIntent.getActivity(
                this.requireContext(),
                1,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
    @SuppressLint("ResourceType")
    private fun init() {
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(this@CalendarFragment.requireContext())
            rcView.adapter = adapter
            iV2.setOnClickListener {
                if(index > 5) index = 0
                val plant = CardDate(listTime[index], listName[index], listTabl[index])
                adapter.addPlant(plant)
                index++
            }
        }
    }
    private fun filter() {
        val filteredlist: ArrayList<CardDate> = ArrayList()

        for (item in dateList) {
            if (item.textTime.toLowerCase().contains(binding.eTsearch.text, ignoreCase = true)) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this@CalendarFragment.requireContext(), "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            adapter.filterList(filteredlist)
        }
    }
}


