package com.kimphuong.manage.ui.home.weekly

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kimphuong.manage.base.toMoney
import com.kimphuong.manage.databinding.LayoutItemWeeklyBinding
import com.kimphuong.manage.db.entity.DataDetail

class WeeklyAdapter(var context: Context, var listData:List<DataDetail>):RecyclerView.Adapter<WeeklyAdapter.WeeklyViewHolder>() {

    inner class WeeklyViewHolder(val binding:LayoutItemWeeklyBinding):ViewHolder(binding.root) {
        fun bind(dataDetail: DataDetail){
            binding.tvPeriod.text = dataDetail.period
            binding.tvIncome.text = dataDetail.income.toMoney()
            binding.tvExpend.text = dataDetail.expend.toMoney()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeeklyViewHolder {
        val binding = LayoutItemWeeklyBinding.inflate(LayoutInflater.from(context))
        return WeeklyViewHolder(binding)
    }



    override fun onBindViewHolder(holder: WeeklyViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size
}