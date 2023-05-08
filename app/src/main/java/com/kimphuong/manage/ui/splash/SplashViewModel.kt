package com.kimphuong.manage.ui.splash

import com.kimphuong.manage.base.BaseViewModel
import com.kimphuong.manage.db.LocalDataSource
import com.kimphuong.manage.db.entity.TypeAccountEntity
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class SplashViewModel @Inject constructor(val localDataSource: LocalDataSource) : BaseViewModel() {

    fun addFirstTypeAccount(list: ArrayList<TypeAccountEntity>) {
        doAsync {
            localDataSource.addAllTypeAccount(list)
        }
    }

}