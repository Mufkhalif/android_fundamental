package com.example.movieapps.ui.movie

import androidx.lifecycle.ViewModel
import com.example.movieapps.data.MovieEntity
import com.example.movieapps.utils.DataDummy

class MovieViewModel : ViewModel() {
    fun getMovies(): List<MovieEntity> = DataDummy.generateDummyMovies()
}