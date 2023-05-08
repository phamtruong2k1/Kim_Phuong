package com.kimphuong.manage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kimphuong.manage.db.dao.UserDao
import com.kimphuong.manage.db.entity.AccountEntity
import com.kimphuong.manage.db.entity.CategoryEntity
import com.kimphuong.manage.db.entity.TransactionEntity
import com.kimphuong.manage.db.entity.TypeAccountEntity

@Database(
    version = 1,
    entities = [
        AccountEntity::class,
        CategoryEntity::class,
        TransactionEntity::class,
        TypeAccountEntity::class
    ]
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
