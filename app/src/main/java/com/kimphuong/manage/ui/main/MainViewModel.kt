package com.kimphuong.manage.ui.main

import com.kimphuong.manage.base.BaseViewModel
import com.kimphuong.manage.db.LocalDataSource
import javax.inject.Inject

class MainViewModel @Inject constructor(val localDataSource: LocalDataSource) : BaseViewModel() {
}