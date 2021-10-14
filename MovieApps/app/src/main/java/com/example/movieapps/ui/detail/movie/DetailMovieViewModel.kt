package com.example.movieapps.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.movieapps.data.local.entity.Movie
import com.example.movieapps.data.MovieRepositorySecond
import com.example.movieapps.vo.Resource

class DetailMovieViewModel(private val movieRepository: MovieRepositorySecond) : ViewModel() {
    private val movieId = MutableLiveData<String>()

    fun setSelectedMovie(movieId: String) {
        this.movieId.value = movieId
    }

    var movie: LiveData<Resource<Movie>> = Transformations.switchMap(movieId) { movieId ->
        movieRepository.getDetailMovie(movieId)
    }

    fun setBookmark() {
        val movieResource = movie.value
        if (movieResource != null) {
            val movieData = movieResource.data
            if (movieData != null) {
                movieRepository.setMovieBookmark(movieData, !movieData.bookmarked)
            }
        }
    }
}