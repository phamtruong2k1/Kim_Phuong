package com.kimphuong.manage.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kimphuong.manage.base.BaseViewModel
import com.kimphuong.manage.db.LocalDataSource
import com.kimphuong.manage.db.entity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(val localDataSource: LocalDataSource) : BaseViewModel() {

    fun getAllTransactionDetail() = localDataSource.getAllTransactionDetail()


}