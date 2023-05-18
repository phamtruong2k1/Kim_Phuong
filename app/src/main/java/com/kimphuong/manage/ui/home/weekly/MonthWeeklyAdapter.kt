package com.kimphuong.manage.ui.home.weekly

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kimphuong.manage.base.getStringMonth
import com.kimphuong.manage.databinding.LayoutItemMonthWeeklyBinding
import com.kimphuong.manage.db.entity.DataDetail

class MonthWeeklyAdapter(var context: Context, var listData: List<Pair<Int, List<DataDetail>>>) :
    RecyclerView.Adapter<MonthWeeklyAdapter.MonthWeekViewHolder>() {
    inner class MonthWeekViewHolder(val binding: LayoutItemMonthWeeklyBinding) :
        ViewHolder(binding.root) {
        fun bind(pair: Pair<Int, List<DataDetail>>) {
            binding.tvMonth.text = context.getStringMonth(pair.first)
            binding.rcvData.apply {
                adapter = WeeklyAdapter(context, pair.second)
                setHasFixedSize(true)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MonthWeeklyAdapter.MonthWeekViewHolder {
        val binding = LayoutItemMonthWeeklyBinding.inflate(LayoutInflater.from(context))
        return MonthWeekViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MonthWeeklyAdapter.MonthWeekViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size

    fun setData(listData: List<Pair<Int, List<DataDetail>>>){
        this.listData = listData
        notifyDataSetChanged()
    }
}