package com.example.movieapps.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.movieapps.data.local.entity.Movie
import com.example.movieapps.data.local.entity.Tv

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentities")
    fun getMovies(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM tventities")
    fun getTvs(): DataSource.Factory<Int, Tv>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvs(movies: List<Tv>)

    @Query("SELECT * FROM movieentities WHERE id = :movieId")
    fun getMoviesById(movieId: String): LiveData<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: Movie)

    @Query("SELECT * FROM tventities WHERE id = :tvId")
    fun getTvById(tvId: String): LiveData<Tv>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tv: Tv)

    @Query("SELECT * FROM movieentities WHERE bookmarked = 1")
    fun getMoviesBookmarked(): DataSource.Factory<Int, Movie>

    @Update
    fun updateMovie(movie: Movie)

    @Query("SELECT * FROM tventities WHERE bookmarked = 1")
    fun getTvsBookmarked(): DataSource.Factory<Int, Tv>

    @Update
    fun updateTv(tv: Tv)
}