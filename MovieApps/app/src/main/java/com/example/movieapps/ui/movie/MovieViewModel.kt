package com.example.movieapps.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.movieapps.data.MovieRepositorySecond
import com.example.movieapps.data.local.entity.Movie
import com.example.movieapps.vo.Resource

class MovieViewModel(private val movieRepository: MovieRepositorySecond) : ViewModel() {
    fun getMovies(): LiveData<Resource<PagedList<Movie>>> = movieRepository.getMovies()
}