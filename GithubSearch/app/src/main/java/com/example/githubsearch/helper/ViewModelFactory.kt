package com.example.githubsearch.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubsearch.ui.detail.DetailViewModel
import com.example.githubsearch.ui.favourite.FavouriteViewModel

class ViewModelFactory private constructor(private val mApplication: Application) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var INSTACE: ViewModelFactory? = null

        @JvmStatic
        fun getInstace(application: Application): ViewModelFactory {
            if (INSTACE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTACE = ViewModelFactory(application)
                }
            }

            return INSTACE as ViewModelFactory
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(FavouriteViewModel::class.java)) {
            return FavouriteViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

}