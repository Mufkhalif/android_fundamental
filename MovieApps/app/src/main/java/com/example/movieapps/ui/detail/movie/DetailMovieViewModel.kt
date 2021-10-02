package com.example.movieapps.ui.detail.movie

import androidx.lifecycle.ViewModel
import com.example.movieapps.data.MovieEntity
import com.example.movieapps.utils.DataDummy

class DetailMovieViewModel : ViewModel() {
    private lateinit var id: String

    fun setSelectedMovie(id: String) {
        this.id = id
    }

    fun getMovie(): MovieEntity {
        lateinit var movie: MovieEntity
        val movies = DataDummy.generateDummyMovies()

        for (movieEntity in movies) {
            if (movieEntity.id == id) {
                movie = movieEntity
            }
        }
        return movie
    }

}