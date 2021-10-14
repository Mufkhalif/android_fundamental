package com.example.movieapps.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.movieapps.data.MovieRepositorySecond
import com.example.movieapps.data.local.entity.Movie
import com.example.movieapps.data.local.entity.Tv

class BookmarkViewModel(private val movieRepositorySecond: MovieRepositorySecond) : ViewModel() {
    fun getMoviesBookmarked(): LiveData<PagedList<Movie>> = movieRepositorySecond.getMoviesBookmarked()

    fun getTvsBookmarked(): LiveData<PagedList<Tv>> = movieRepositorySecond.getTvsBookmarked()
}