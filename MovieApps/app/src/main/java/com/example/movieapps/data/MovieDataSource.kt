package com.example.movieapps.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.movieapps.data.local.entity.Movie
import com.example.movieapps.data.local.entity.Tv
import com.example.movieapps.vo.Resource

interface MovieDataSource {
    fun getMovies(): LiveData<Resource<PagedList<Movie>>>

    fun getTvs(): LiveData<Resource<PagedList<Tv>>>

    fun getDetailMovie(id: String): LiveData<Resource<Movie>>

    fun getDetailTv(id: String): LiveData<Resource<Tv>>

    fun getMoviesBookmarked(): LiveData<PagedList<Movie>>

    fun setMovieBookmark(movie: Movie, state: Boolean)

    fun getTvsBookmarked(): LiveData<PagedList<Tv>>

    fun setTvBookmark(tv: Tv, state: Boolean)

}