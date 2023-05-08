package com.kimphuong.manage.ui.account

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.kimphuong.manage.R
import com.kimphuong.manage.db.entity.TypeAccountEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TypeAccountAdapter(
    var context: Context,
    var listData: List<TypeAccountEntity>,
    var pos: Int,
    private val listener: TypeAccountAdapterListener
) : RecyclerView.Adapter<TypeAccountAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var root: LinearLayout = view.findViewById(R.id.llRoot)
        var txtTitleType: TextView = view.findViewById(R.id.txtTitleType)
        var imgChoose: ImageView = view.findViewById(R.id.imgChoose)
        var imgIcon: ImageView = view.findViewById(R.id.imgIcon)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_choose_type_account, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data = listData[position]

        viewHolder.txtTitleType.text = data.name
        viewHolder.imgIcon.setImageResource(data.icon)

        if (position == pos) {
            viewHolder.imgChoose.visibility = View.VISIBLE
            viewHolder.txtTitleType.setTextColor(context.getColor(R.color.color_app))
        } else {
            viewHolder.imgChoose.visibility = View.GONE
        }

        viewHolder.root.setOnClickListener {
            listener.click(position)
        }

    }

    override fun getItemCount() = listData.size

}

interface TypeAccountAdapterListener {
    fun click(position: Int)
}