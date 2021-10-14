package com.example.movieapps.di

import android.content.Context
import com.example.movieapps.api.ApiConfig
import com.example.movieapps.data.MovieRepositorySecond
import com.example.movieapps.data.local.LocalDataSource
import com.example.movieapps.data.local.room.MovieDatabase
import com.example.movieapps.data.remote.RemoteDataSource
import com.example.movieapps.utils.AppExecutors

object Injection {
    fun providerRepository(context: Context): MovieRepositorySecond {
        val database = MovieDatabase.getInstance(context)

        val remoteRepository = RemoteDataSource()
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepositorySecond.getInstance(remoteRepository, localDataSource, appExecutors)
    }
}