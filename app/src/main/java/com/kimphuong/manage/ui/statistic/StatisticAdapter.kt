package com.kimphuong.manage.ui.statistic

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kimphuong.manage.databinding.ItemStatisticBinding
import com.kimphuong.manage.db.entity.DataStatistic
import org.jetbrains.anko.backgroundColor

class StatisticAdapter(var context: Context, var listData: List<DataStatistic>, var total: Float) :
    RecyclerView.Adapter<StatisticAdapter.StatisticViewHolder>() {

    inner class StatisticViewHolder(val binding: ItemStatisticBinding) : ViewHolder(binding.root) {
        fun bind(dataStatistic: DataStatistic) {
            if (total!=0f){
                binding.tvAmount.text = dataStatistic.amount.toString()
                binding.tvCategoryName.text = dataStatistic.name
                binding.imgIcon.setImageDrawable(dataStatistic.icon)
                binding.tvPercent.text = String.format("%.1f%%", dataStatistic.amount / total * 100)
                binding.tvPercent.backgroundColor = dataStatistic.color
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StatisticViewHolder {
        val binding = ItemStatisticBinding.inflate(LayoutInflater.from(context))
        return StatisticViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatisticViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size

    fun setData(listData: List<DataStatistic>, sum:Float){
        this.listData = listData
        total = sum
        notifyDataSetChanged()
    }
}