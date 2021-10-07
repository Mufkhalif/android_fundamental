package com.example.movieapps.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieapps.api.response.ResponseMovie
import com.example.movieapps.data.remote.MovieRepositorySecond

class MovieViewModel(private val movieRepository: MovieRepositorySecond) : ViewModel() {
    fun getMovies(): LiveData<ResponseMovie> = movieRepository.getMovies()
}