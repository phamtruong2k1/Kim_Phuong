package com.kimphuong.manage.ui.enterdata

import com.kimphuong.manage.base.BaseViewModel
import com.kimphuong.manage.db.LocalDataSource
import com.kimphuong.manage.db.entity.TransactionEntity
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class EnterDataViewModel @Inject constructor(val localDataSource: LocalDataSource) :
    BaseViewModel() {

    fun saveEnterData(transactionEntity: TransactionEntity) {
        doAsync {
            localDataSource.addTransaction(transactionEntity)
        }
    }

    fun getTransactionById(id: Int)  = localDataSource.getTransactionById(id)

    fun getCategoryById(id : Int) = localDataSource.getCategoryById(id)

    fun getAccountById(id: Int) = localDataSource.getAccountById(id)

    fun deleteTransaction(transactionEntity: TransactionEntity) {
        doAsync {
            localDataSource.deleteTransaction(transactionEntity)
        }
    }
}