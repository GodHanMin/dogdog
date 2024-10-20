package com.example.dog_cat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalendarAdapter(private val days: List<Int>) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    inner class CalendarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textDate: TextView = view.findViewById(R.id.textDate)
        val iconSmile: ImageView = view.findViewById(R.id.iconSmile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_calendar_item, parent, false)
        return CalendarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.textDate.text = days[position].toString()
        // 필요한 경우에만 아이콘을 표시
//        holder.iconSmile.visibility = if (days[position] % 2 == 0) View.VISIBLE else View.GONE
    }

    override fun getItemCount(): Int {
        return days.size
    }
}