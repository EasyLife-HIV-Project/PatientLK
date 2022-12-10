package aaa.bbb.registration.fragments

import aaa.bbb.registration.databinding.FragmentMaterialBinding
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.*


class CalendarFragment : Fragment() {
    private lateinit var binding: FragmentMaterialBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMaterialBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bt1.setOnClickListener  {
            val calendar: Calendar = Calendar.getInstance()
            if (Build.VERSION.SDK_INT  >= 23) {
                calendar.set(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    binding.timePicker.hour,

                    binding.timePicker.minute,
                    0
                )
            } else {

                calendar.set(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),

                    binding.timePicker.currentHour,
                    binding.timePicker.currentMinute, 0
                )
            }
            setAlarm(calendar.timeInMillis)
        }
    }
    private class MyAlarm : BroadcastReceiver() {
        override fun onReceive(

            context: Context,

            intent: Intent
        ) {
            Log.d("Alarm Bell", "Alarm just fired")
        }
    }

    private fun setAlarm(timeInMillis: Long) {
        val alarmManager  = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent  = Intent (this.requireContext(), MyAlarm::class.java )
        val pendingIntent  = PendingIntent.getBroadcast(this.requireContext(), 0, intent, 0)
        alarmManager.setRepeating(
            AlarmManager.RTC,
            timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        Toast.makeText (this.requireContext(), "Напоминание установлено!", Toast.LENGTH_SHORT).show()
    }
}
