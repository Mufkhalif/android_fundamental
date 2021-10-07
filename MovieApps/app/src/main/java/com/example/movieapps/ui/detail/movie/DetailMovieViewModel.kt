package com.example.movieapps.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieapps.api.model.Movie
import com.example.movieapps.data.remote.MovieRepositorySecond

class DetailMovieViewModel(private val movieRepository: MovieRepositorySecond) : ViewModel() {

    fun getDetailMovie(id: String): LiveData<Movie> = movieRepository.getDetailMovie(id)

}