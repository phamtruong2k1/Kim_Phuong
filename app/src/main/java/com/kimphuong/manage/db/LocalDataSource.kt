package com.kimphuong.manage.db

import com.kimphuong.manage.db.dao.UserDao
import com.kimphuong.manage.db.entity.AccountEntity
import com.kimphuong.manage.db.entity.CategoryEntity
import com.kimphuong.manage.db.entity.TransactionEntity
import com.kimphuong.manage.db.entity.TypeAccountEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val userDao: UserDao) {

    fun getAllTypeAccount() = userDao.getAllTypeAccount()
    fun addAllTypeAccount(listData: ArrayList<TypeAccountEntity>) =
        userDao.addAllTypeAccount(listData)

    fun getAccountByType(type_id: Int) = userDao.getAccountByType(type_id)

    fun getTransactionById(id: Int) = userDao.getTransactionById(id)

    fun getCategoryById(id: Int) = userDao.getCategoryById(id)
    fun getAccountById(id: Int) = userDao.getAccountById(id)
    fun getAllAccount() = userDao.getAllAccount()
    fun addAccount(data: AccountEntity) = userDao.addAccount(data)
    fun deleteAccount(accountEntity: AccountEntity) = userDao.deleteAccount(accountEntity)


    //fun getAccountByType(type_id : Int) = userDao.getAccountByType(type_id)
    fun getAllCategory() = userDao.getAllCategory()
    fun getListCategory(type: Boolean) = userDao.getListCategory(type)
    fun addCategory(data: CategoryEntity) = userDao.addCategory(data)
    fun addAllCategory(data: List<CategoryEntity>) = userDao.addAllCategory(data)
    fun deleteCategory(categoryEntity: CategoryEntity) = userDao.deleteCategory(categoryEntity)


    fun addTransaction(data: TransactionEntity) = userDao.addTransaction(data)

    fun getAllTransactionDetail() = userDao.getAllTransactionDetail()

    fun deleteTransaction(transactionEntity: TransactionEntity) = userDao.deleteTransaction(transactionEntity)
    fun getAllTransaction() = userDao.getAllTransaction()

    suspend fun deleteAllData() = withContext(Dispatchers.IO) {
        userDao.deleteAllAccount()
        userDao.deleteAllCategory()
        userDao.deleteAllTransaction()
        userDao.deleteAllTypeAccount()
    }

    suspend fun insertAllData(listAccount:List<AccountEntity>, listTypeAccount:List<TypeAccountEntity>, listCategory:List<CategoryEntity>, listTransaction:List<TransactionEntity>)
        = withContext(Dispatchers.IO){
        userDao.insertAllTypeAccount(listTypeAccount)
        userDao.insertAllAccount(listAccount)
        userDao.insertAllCategory(listCategory)
        userDao.insertAllTransaction(listTransaction)
    }


}