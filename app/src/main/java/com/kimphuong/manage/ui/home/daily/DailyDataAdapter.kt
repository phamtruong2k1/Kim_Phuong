package com.kimphuong.manage.ui.home.daily

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kimphuong.manage.R
import com.kimphuong.manage.databinding.LayoutItemDailydataBinding
import com.kimphuong.manage.db.entity.TransactionDetail

class DailyDataAdapter(
    var context: Context, var listData: List<TransactionDetail>,
    var callbackClickItem: ((Int) -> Unit)? = null
) : RecyclerView.Adapter<DailyDataAdapter.DailyViewHolder>() {

    private var isSearch = false

    inner class DailyViewHolder(val binding: LayoutItemDailydataBinding) :
        ViewHolder(binding.root) {
        fun bind(data: TransactionDetail) {
            binding.imgIcon.setImageResource(data.category_icon)
            binding.tvAccount.text = "(${if (isSearch) data.note else data.account_name})"
            binding.tvAmount.text = data.amount.toInt().toString()
            binding.tvAmount.setTextColor(
                if (!data.type) Color.RED else ContextCompat.getColor(
                    context,
                    R.color.color_app
                )
            )
            binding.tvCategory.text = data.category_name
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DailyDataAdapter.DailyViewHolder {
        val binding = LayoutItemDailydataBinding.inflate(LayoutInflater.from(context))
        val holder = DailyViewHolder(binding)
        initListener(holder)
        return holder
    }

    private fun initListener(holder: DailyDataAdapter.DailyViewHolder) {
        holder.binding.rlRoot.setOnClickListener {
            callbackClickItem?.invoke(listData[holder.layoutPosition].transaction_id)
        }
    }

    override fun onBindViewHolder(holder: DailyDataAdapter.DailyViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount() = listData.size

    fun setData(listData: List<TransactionDetail>, isSearch: Boolean = false) {
        this.isSearch = isSearch
        this.listData = listData
        notifyDataSetChanged()
    }
}