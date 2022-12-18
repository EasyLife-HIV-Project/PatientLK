package aaa.bbb.registration.fragments

import aaa.bbb.registration.R
import aaa.bbb.registration.databinding.CalendarItemBinding
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class CalendarAdapter: RecyclerView.Adapter<CalendarAdapter.CalendarHolder>() {
        var dateList = ArrayList<CardDate>()
        class CalendarHolder(item: View): RecyclerView.ViewHolder(item) {
            val binding = CalendarItemBinding.bind(item)
            fun bind(calendar: CardDate) = with(binding){
                tName.text = calendar.textName
                tTime.text = calendar.textTime
                tTabl.text = calendar.textTabl
            }
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.calendar_item, parent, false)
            return CalendarHolder(view)
        }

        override fun onBindViewHolder(holder: CalendarHolder, position: Int) {
            holder.bind(dateList[position])
        }

        override fun getItemCount(): Int {
            return dateList.size
        }

        fun addPlant(plant: CardDate){
            dateList.add(plant)
            notifyDataSetChanged()
        }

    fun filterList(filterlist: ArrayList<CardDate>) {
        dateList = filterlist
        notifyDataSetChanged()
    }
    }