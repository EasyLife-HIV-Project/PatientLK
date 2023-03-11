package aaa.bbb.registration.fragments

import aaa.bbb.registration.R
import aaa.bbb.registration.databinding.CalendarItemBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

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