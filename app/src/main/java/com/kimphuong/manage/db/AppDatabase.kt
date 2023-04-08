package com.kimphuong.manage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kimphuong.manage.db.dao.UserDao
import com.kimphuong.manage.db.entity.UserEntity

@Database(
    version = 1,
    entities = [
        UserEntity::class
    ]
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
