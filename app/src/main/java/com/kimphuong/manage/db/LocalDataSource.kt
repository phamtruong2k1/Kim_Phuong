package com.kimphuong.manage.db

import com.kimphuong.manage.db.dao.UserDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val userDao: UserDao) {

//    fun deleteFavorite(favoriteEntity: FavoriteEntity) = userDao.deleteFavorite(favoriteEntity)

}