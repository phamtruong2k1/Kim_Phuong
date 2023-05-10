package com.kimphuong.manage.ui.account

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kimphuong.manage.R
import com.kimphuong.manage.db.dao.UserDao
import com.kimphuong.manage.db.entity.AccountEntity
import com.kimphuong.manage.db.entity.TypeAccountEntity
import com.kimphuong.manage.utils.gone
import com.kimphuong.manage.utils.show

class ShowAccountAdapter(
    var context: Context,
    var listData: List<TypeAccountEntity>,
    var listAccount: List<AccountEntity>,
    private val listener: ShowAccountAdapterListener
) : RecyclerView.Adapter<ShowAccountAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var llRoot: LinearLayout = view.findViewById(R.id.llRoot)
        var txtName: TextView = view.findViewById(R.id.txtName)
        var txtAmount: TextView = view.findViewById(R.id.txtAmount)
        var rcyAccount: RecyclerView = view.findViewById(R.id.rcyAccount)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_item_show_account_adapter, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data = listData[position]

        viewHolder.txtName.text = data.name

        getData(data.type_account_id, viewHolder.llRoot, viewHolder.txtAmount, viewHolder.rcyAccount)

    }

    private fun getData(
        type_id: Int,
        llRoot: LinearLayout,
        txtAmount: TextView,
        rcyAccount: RecyclerView
    ){
        val list = ArrayList<AccountEntity>()
        listAccount.forEach {
            if (it.type_account_id == type_id) {
                list.add(it)
            }
        }
        if (list.size > 0) {
            llRoot.show()
            val adapter = ListAccountAdapter(context, list, object : ListAccountAdapterListener{
                override fun click(account : AccountEntity) {
                    listener.click(account)
                }

                override fun longClick(account: AccountEntity) {
                    listener.longClick(account)
                }
            })
            rcyAccount.adapter = adapter
        } else {
            llRoot.gone()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<TypeAccountEntity>, listAcc: List<AccountEntity>) {
        listData = list
        listAccount = listAcc
        notifyDataSetChanged()
    }

    override fun getItemCount() = listData.size

}

interface ShowAccountAdapterListener {
    fun click(account : AccountEntity)
    fun longClick(account : AccountEntity)
}