package com.kimphuong.manage.base

import androidx.lifecycle.ViewModel


open class BaseViewModel internal constructor() : ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }
}
