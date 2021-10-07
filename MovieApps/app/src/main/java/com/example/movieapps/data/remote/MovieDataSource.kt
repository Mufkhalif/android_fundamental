package com.example.movieapps.data.remote

import androidx.lifecycle.LiveData
import com.example.movieapps.api.model.Movie
import com.example.movieapps.api.model.Tv
import com.example.movieapps.api.response.ResponseMovie
import com.example.movieapps.api.response.ResponseTv

interface MovieDataSource {
    fun getMovies(): LiveData<ResponseMovie>

    fun getTvs(): LiveData<ResponseTv>

    fun getDetailMovie(id: String): LiveData<Movie>

    fun getDetailTv(id: String): LiveData<Tv>
}