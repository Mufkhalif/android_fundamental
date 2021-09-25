package com.example.githubsearch.database

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserFavouriteRepository(application: Application) {

    private val mUserFavouriteDao: UserFavouriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = UserFavouriteRoomDatabase.getDatabase(application)
        mUserFavouriteDao = db.userFavouriteDao()
    }


    fun insert(user: UserFavourite) {
        executorService.execute { mUserFavouriteDao.insert(user) }
    }

    fun delete(user: UserFavourite) {
        executorService.execute { mUserFavouriteDao.delete(user) }
    }

    fun update(user: UserFavourite) {
        executorService.execute { mUserFavouriteDao.update(user) }
    }

    fun getAllUserFavourites(): LiveData<List<UserFavourite>> =
        mUserFavouriteDao.getAllUserFavourites()

    fun getUserFavouriteByUsername(login: String?): LiveData<List<UserFavourite>> =
        mUserFavouriteDao.getUserFavouriteByUsername(login)
}