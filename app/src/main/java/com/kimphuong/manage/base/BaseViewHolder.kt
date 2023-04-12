package com.kimphuong.manage.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BaseViewHolder<T : ViewBinding>(binding: T) : RecyclerView.ViewHolder(binding.root) {
    open fun onBind(position: Int) {

    }
}