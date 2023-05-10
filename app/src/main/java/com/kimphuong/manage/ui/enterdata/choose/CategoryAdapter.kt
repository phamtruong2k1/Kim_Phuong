package com.kimphuong.manage.ui.enterdata.choose

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
import com.kimphuong.manage.db.entity.AccountEntity
import com.kimphuong.manage.db.entity.CategoryEntity
import com.kimphuong.manage.db.entity.TypeAccountEntity
import com.kimphuong.manage.ui.account.ListAccountAdapter
import com.kimphuong.manage.utils.DataUtil

class CategoryAdapter(
    var context: Context,
    var listData: List<CategoryEntity>,
    private val listener: CategoryAdapterListener
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var root: LinearLayout = view.findViewById(R.id.llRoot)
        var txtTitleType: TextView = view.findViewById(R.id.txtTitleType)
        var imgIcon: ImageView = view.findViewById(R.id.imgIcon)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_category_adapter, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data = listData[position]

        viewHolder.txtTitleType.text = data.name
        viewHolder.imgIcon.setImageResource(
            data.icon
        )

        viewHolder.root.setOnClickListener {
            listener.click(data)
        }

        viewHolder.root.setOnLongClickListener {
            listener.longClick(data)
            true
        }

    }

    override fun getItemCount() = listData.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<CategoryEntity>) {
        listData = list
        notifyDataSetChanged()
    }

}

interface CategoryAdapterListener {
    fun click(categoryEntity : CategoryEntity)
    fun longClick(categoryEntity : CategoryEntity)
}