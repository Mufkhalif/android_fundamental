package com.example.movieapps.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieapps.api.response.ResponseTv
import com.example.movieapps.data.remote.MovieRepositorySecond

class TvViewModel(private val movieRepository: MovieRepositorySecond) : ViewModel() {
    fun getTv(): LiveData<ResponseTv> = movieRepository.getTvs()
}