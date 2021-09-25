package com.example.githubsearch.ui.favourite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubsearch.database.UserFavourite
import com.example.githubsearch.database.UserFavouriteRepository

class FavouriteViewModel(application: Application) : ViewModel() {
    private val mUserFavouriteRepository: UserFavouriteRepository =
        UserFavouriteRepository(application)

    fun getAllFavourite(): LiveData<List<UserFavourite>> =
        mUserFavouriteRepository.getAllUserFavourites()

}