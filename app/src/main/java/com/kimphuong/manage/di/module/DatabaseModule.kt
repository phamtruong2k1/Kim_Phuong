package com.kimphuong.manage.di.module

import android.content.Context
import androidx.room.Room
import com.kimphuong.manage.db.AppDatabase
import com.kimphuong.manage.db.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {
    val DATABASE_NAME = "kim_phuong_01"


    @Singleton
    @Provides
    fun getDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, DATABASE_NAME
        ).addMigrations(/*MIGRATION_1_2,MIGRATION_2_3*/)
            .build()
    }

    @Singleton
    @Provides
    fun provideCategoryDao(db: AppDatabase): UserDao {
        return db.userDao()
    }

}
