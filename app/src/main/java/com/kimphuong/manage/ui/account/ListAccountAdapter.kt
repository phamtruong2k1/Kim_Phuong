package com.kimphuong.manage.ui.account

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kimphuong.manage.R
import com.kimphuong.manage.db.entity.AccountEntity
import com.kimphuong.manage.db.entity.TypeAccountEntity
import com.kimphuong.manage.utils.DataUtil

class ListAccountAdapter(
    var context: Context,
    var listData: List<AccountEntity>,
    private val listener: ListAccountAdapterListener
) : RecyclerView.Adapter<ListAccountAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var root: LinearLayout = view.findViewById(R.id.llRoot)
        var txtTitleType: TextView = view.findViewById(R.id.txtTitleType)
        var txtAmount: TextView = view.findViewById(R.id.txtAmount)
        var imgIcon: ImageView = view.findViewById(R.id.imgIcon)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_list_account_adapter, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data = listData[position]

        viewHolder.txtTitleType.text = data.name
        viewHolder.imgIcon.setImageResource(
            DataUtil.listTypeAccount[data.type_account_id-1].icon
        )

        viewHolder.txtAmount.text = data.amount.toString()

        viewHolder.root.setOnLongClickListener {
            listener.click(data)
            true
        }

    }

    override fun getItemCount() = listData.size

}

interface ListAccountAdapterListener {
    fun click(account : AccountEntity)
}