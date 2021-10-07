package com.example.movieapps.di

import com.example.movieapps.api.ApiConfig
import com.example.movieapps.data.remote.MovieRepositorySecond
import com.example.movieapps.data.remote.RemoteDataSource

object Injection {
    fun providerRepository(): MovieRepositorySecond {
        val remoteRepository = RemoteDataSource.getInstance(ApiConfig())
        return MovieRepositorySecond.getInstance(remoteRepository)
    }
}