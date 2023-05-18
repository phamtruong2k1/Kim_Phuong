package com.kimphuong.manage.ui.home.calender

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kimphuong.manage.R
import com.kimphuong.manage.databinding.ItemCalendarBinding
import com.kimphuong.manage.db.entity.DayData

class CalendarAdapter(var context: Context, var listData: List<DayData>) :
    RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    inner class CalendarViewHolder(val binding: ItemCalendarBinding) : ViewHolder(binding.root) {
        fun bind(data: DayData) {
            if (data.isToday) {
                binding.tvDay.setBackgroundColor(ContextCompat.getColor(context, R.color.color_app))
                binding.tvDay.setTextColor(Color.WHITE)
            }
            if ((bindingAdapterPosition+1)%7==0){
                binding.vDivider.visibility = View.GONE
            }else{
                binding.vDivider.visibility = View.VISIBLE
            }
            binding.tvDay.text = data.day.toString()
            binding.tvIncome.text = data.income.toString()
            binding.tvExpend.text = data.expend.toString()
            binding.tvTotal.text = (data.income - data.expend).toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalendarViewHolder {
        val binding = ItemCalendarBinding.inflate(LayoutInflater.from(context))
        return CalendarViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size

    fun setData(listData: List<DayData>) {
        this.listData = listData
        notifyDataSetChanged()
    }
}