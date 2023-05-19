package com.kimphuong.manage.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kimphuong.manage.db.entity.*

@Dao
interface UserDao {

    //Type Account

    @Query("SELECT * FROM type_account")
    fun getAllTypeAccount(): LiveData<MutableList<TypeAccountEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllTypeAccount(listData: ArrayList<TypeAccountEntity>): List<Long>

    //Account
    @Query("SELECT * FROM account")
    fun getAllAccount(): LiveData<MutableList<AccountEntity>>

    @Query("SELECT * FROM account WHERE account_id = :id ")
    fun getAccountById(id: Int): LiveData<AccountEntity>

    @Query("SELECT * FROM category WHERE category_id = :id ")
    fun getCategoryById(id: Int): LiveData<CategoryEntity>

    @Query("SELECT * FROM account WHERE account_id = :type_id")
    fun getAccountByType(type_id: Int): LiveData<MutableList<AccountEntity>>

    @Query("SELECT * FROM `transaction` WHERE transaction_id = :id")
    fun getTransactionById(id: Int): LiveData<TransactionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAccount(data: AccountEntity): Long

    @Delete
    fun deleteAccount(accountEntity: AccountEntity): Int

    //Category
    @Query("SELECT * FROM category")
    fun getAllCategory(): LiveData<MutableList<CategoryEntity>>

    @Query("SELECT * FROM category WHERE type = :type")
    fun getListCategory(type: Boolean): LiveData<MutableList<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCategory(data: CategoryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllCategory(data: List<CategoryEntity>): List<Long>

    @Delete
    fun deleteCategory(categoryEntity: CategoryEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTransaction(data: TransactionEntity): Long

    @Query("SELECT transaction_id, day, month, year, amount, `transaction`.type, note, account_name, category_name, category_icon  FROM `transaction` INNER JOIN account ON `transaction`.account_id = `account`.account_id INNER JOIN category ON `category`.category_id = `transaction`.category_id")
    fun getAllTransactionDetail(): LiveData<MutableList<TransactionDetail>>

    @Delete
    fun deleteTransaction(transactionEntity: TransactionEntity): Int

    @Query("SELECT * FROM `transaction`")
    fun getAllTransaction(): LiveData<MutableList<TransactionEntity>>

    @Query("DELETE FROM `transaction`")
    fun deleteAllTransaction()

    @Query("DELETE FROM category")
    fun deleteAllCategory()

    @Query("DELETE FROM account")
    fun deleteAllAccount()

    @Query("DELETE FROM type_account")
    fun deleteAllTypeAccount()

    @Insert
    suspend fun insertAllAccount(data: List<AccountEntity>)

    @Insert
    suspend fun insertAllCategory(data: List<CategoryEntity>)

    @Insert
    suspend fun insertAllTypeAccount(data: List<TypeAccountEntity>)

    @Insert
    suspend fun insertAllTransaction(data: List<TransactionEntity>)

}
