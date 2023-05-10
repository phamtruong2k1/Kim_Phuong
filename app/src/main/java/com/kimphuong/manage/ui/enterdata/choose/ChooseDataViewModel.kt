package com.kimphuong.manage.ui.enterdata.choose

import com.kimphuong.manage.base.BaseViewModel
import com.kimphuong.manage.db.LocalDataSource
import com.kimphuong.manage.db.entity.AccountEntity
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class ChooseDataViewModel @Inject constructor(val localDataSource: LocalDataSource) : BaseViewModel() {

    fun getListCategory(type : Boolean) = localDataSource.getListCategory(type)


    fun getAllAccount() = localDataSource.getAllAccount()


    fun deleteAccount(account: AccountEntity){
        doAsync {
            localDataSource.deleteAccount(account)
        }
    }

}