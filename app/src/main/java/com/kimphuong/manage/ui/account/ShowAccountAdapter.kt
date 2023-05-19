package com.kimphuong.manage.ui.account

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kimphuong.manage.R
import com.kimphuong.manage.base.toMoney
import com.kimphuong.manage.databinding.LayoutItemShowAccountAdapterBinding
import com.kimphuong.manage.databinding.LayoutListAccountAdapterBinding
import com.kimphuong.manage.db.entity.AccountEntity
import com.kimphuong.manage.db.entity.TypeAccountEntity
import com.kimphuong.manage.utils.gone

class ShowAccountAdapter(
    var context: Context,
    var listData: List<Pair<TypeAccountEntity, List<AccountEntity>>>,
    private val listener: ShowAccountAdapterListener
) : RecyclerView.Adapter<ShowAccountAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: LayoutItemShowAccountAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Pair<TypeAccountEntity, List<AccountEntity>>) {
            val sum = data.second.sumOf { it.amount.toInt() }
            binding.txtAmount.text = sum.toMoney()
            if (sum < 0) {
                binding.txtAmount.setTextColor(Color.RED)
            } else {
                binding.txtAmount.setTextColor(ContextCompat.getColor(context, R.color.color_app))
            }

            binding.txtName.text = data.first.name

            val adapter = ListAccountAdapter(
                context,
                data.first,
                data.second,
                object : ListAccountAdapterListener {
                    override fun click(account: AccountEntity) {
                        listener.click(account)
                    }

                    override fun longClick(account: AccountEntity) {
                        listener.longClick(account)
                    }
                })
            binding.rcyAccount.adapter = adapter
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutItemShowAccountAdapterBinding.inflate(LayoutInflater.from(context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(listData[position])
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(listData: List<Pair<TypeAccountEntity, List<AccountEntity>>>) {
        this.listData = listData
        notifyDataSetChanged()
    }

    override fun getItemCount() = listData.size

}

interface ShowAccountAdapterListener {
    fun click(account: AccountEntity)
    fun longClick(account: AccountEntity)
}