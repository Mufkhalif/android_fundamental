package com.example.movieapps.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.movieapps.data.local.entity.Movie
import com.example.movieapps.data.local.entity.Tv
import com.example.movieapps.data.local.room.MovieDao

class LocalDataSource private constructor(private val mMovieDao: MovieDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource = INSTANCE ?: LocalDataSource(movieDao)
    }

    fun getMovies(): DataSource.Factory<Int, Movie> = mMovieDao.getMovies()

    fun getTvs(): DataSource.Factory<Int, Tv> = mMovieDao.getTvs()

    fun insertMovies(movies: List<Movie>) = mMovieDao.insertMovies(movies)

    fun insertTvs(tvs: List<Tv>) = mMovieDao.insertTvs(tvs)

    fun getMovieById(movieId: String): LiveData<Movie> = mMovieDao.getMoviesById(movieId)

    fun insertMovie(movie: Movie) = mMovieDao.insertMovie(movie)

    fun getTvById(tvId: String): LiveData<Tv> = mMovieDao.getTvById(tvId)

    fun insertTv(tv: Tv) = mMovieDao.insertTv(tv)

    fun getMoviesBookmarked(): DataSource.Factory<Int, Movie> = mMovieDao.getMoviesBookmarked()

    fun setMovieBookmark(movie: Movie, state: Boolean) {
        movie.bookmarked = state
        mMovieDao.updateMovie(movie)
    }

    fun getTvsBookmarked(): DataSource.Factory<Int, Tv> = mMovieDao.getTvsBookmarked()

    fun setTvBookmark(tv: Tv, state: Boolean) {
        tv.bookmarked = state
        mMovieDao.updateTv(tv)
    }


}