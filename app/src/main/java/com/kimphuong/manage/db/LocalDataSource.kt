package com.kimphuong.manage.db

import com.kimphuong.manage.db.dao.UserDao
import com.kimphuong.manage.db.entity.AccountEntity
import com.kimphuong.manage.db.entity.TypeAccountEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val userDao: UserDao) {

    fun getAllTypeAccount() = userDao.getAllTypeAccount()
    fun addAllTypeAccount(listData: ArrayList<TypeAccountEntity>) = userDao.addAllTypeAccount(listData)

    fun getAccountByType(type_id : Int) = userDao.getAccountByType(type_id)
    fun addAccount(data: AccountEntity) = userDao.addAccount(data)

}