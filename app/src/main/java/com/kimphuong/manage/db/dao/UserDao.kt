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
    fun  getTransactionById(id: Int): LiveData<TransactionEntity>

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

//    @Query("SELECT * FROM meme")
//    fun getAllMeme(): MutableList<MemeEntity>
//
//    @Query("SELECT Count(*) FROM meme")
//    fun getCountMeme(): LiveData<Int>
//
//    @Query(
//        "SELECT * FROM meme " +
//                "WHERE tab in (:list) " +
//                "ORDER BY \n" +
//                "CASE WHEN :isAsc = 1 THEN name END ASC,\n" +
//                "CASE WHEN :isAsc = 2 THEN name END DESC"
//    )
//    fun getTabMeme(list: List<String>, isAsc: Int): MutableList<MemeEntity>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun addAllMeme(listData: ArrayList<MemeEntity>): List<Long>
//
//    @Query("delete from meme where id in (:listData)")
//    fun deleteAllMeme(listData: List<String>)
//
//    @Query("SELECT id FROM meme")
//    fun getAllIdOfMeme(): MutableList<String>
//
////    @Query("delete from meme")
////    fun deleteAllMeme() : List<Long>
//
//    @Query(
//        "SELECT * FROM user " +
//                "ORDER BY \n" +
//                "CASE WHEN :isAsc = 1 THEN name END ASC ,\n" +
//                "CASE WHEN :isAsc = 2 THEN name END DESC"
//    )
//    fun getListUser(isAsc: Int): MutableList<UserEntity>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun addUser(userEntity: UserEntity): Long
//
//    @Delete
//    fun deleteUser(userEntity: UserEntity): Int
//
//    @Query(
//        "SELECT * FROM favorite " +
//                "ORDER BY \n" +
//                "CASE WHEN :isAsc = 1 THEN name END ASC ,\n" +
//                "CASE WHEN :isAsc = 2 THEN name END DESC"
//    )
//    fun getListFavorite(isAsc: Int): MutableList<FavoriteEntity>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun addFavorite(favoriteEntity: FavoriteEntity): Long
//
//    @Delete
//    fun deleteFavorite(favoriteEntity: FavoriteEntity): Int
}
