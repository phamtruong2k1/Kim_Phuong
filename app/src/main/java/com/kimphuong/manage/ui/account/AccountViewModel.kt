package com.kimphuong.manage.ui.account

import com.kimphuong.manage.base.BaseViewModel
import com.kimphuong.manage.db.LocalDataSource
import com.kimphuong.manage.db.entity.AccountEntity
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class AccountViewModel @Inject constructor(val localDataSource: LocalDataSource) : BaseViewModel() {

    fun addAccount(account : AccountEntity) {
        doAsync {
            localDataSource.addAccount(account)
        }
    }

    fun getAllAccount() = localDataSource.getAllAccount()

    fun getAllTransaction() = localDataSource.getAllTransaction()

    fun deleteAccount(account: AccountEntity){
        doAsync {
            localDataSource.deleteAccount(account)
        }
    }

}