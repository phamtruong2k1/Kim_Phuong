package com.kimphuong.manage.ui.account

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kimphuong.manage.base.toMoney
import com.kimphuong.manage.databinding.LayoutListAccountAdapterBinding
import com.kimphuong.manage.db.entity.AccountEntity
import com.kimphuong.manage.db.entity.TypeAccountEntity
import com.kimphuong.manage.utils.DataUtil

class ListAccountAdapter(
    var context: Context,
    var type: TypeAccountEntity,
    var listData: List<AccountEntity>,
    private val listener: ListAccountAdapterListener
) : RecyclerView.Adapter<ListAccountAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: LayoutListAccountAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AccountEntity) {
            binding.txtTitleType.text = data.name
            binding.imgIcon.setImageResource(
                DataUtil.listTypeAccount[type.type_account_id - 1].icon
            )
            binding.txtAmount.text = data.amount.toInt().toMoney()
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutListAccountAdapterBinding.inflate(LayoutInflater.from(context))
        val viewHolder = ViewHolder(binding)
        setOnClick(viewHolder)
        return viewHolder
    }

    private fun setOnClick(viewHolder: ListAccountAdapter.ViewHolder) {
        viewHolder.binding.llRoot.setOnClickListener {
            listener.click(listData[viewHolder.bindingAdapterPosition])
        }

        viewHolder.binding.llRoot.setOnLongClickListener {
            listener.longClick(listData[viewHolder.bindingAdapterPosition])
            true
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(listData[position])
    }

    override fun getItemCount() = listData.size

}

interface ListAccountAdapterListener {
    fun click(account: AccountEntity)
    fun longClick(account: AccountEntity)
}