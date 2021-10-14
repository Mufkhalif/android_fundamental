package com.example.movieapps.api

import com.example.movieapps.BuildConfig
import com.example.movieapps.data.local.entity.Movie
import com.example.movieapps.data.local.entity.Tv
import com.example.movieapps.api.response.ResponseMovie
import com.example.movieapps.api.response.ResponseTv
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/popular?api_key=${BuildConfig.API_KEY}")
    fun getMovie(): Call<ResponseMovie>

    @GET("tv/popular?api_key=${BuildConfig.API_KEY}")
    fun getTv(): Call<ResponseTv>

    @GET("movie/{movie}?api_key=${BuildConfig.API_KEY}")
    fun getDetailMovie(
        @Path("movie") movie: String
    ): Call<Movie>

    @GET("tv/{tv}?api_key=${BuildConfig.API_KEY}")
    fun getDetailTv(
        @Path("tv") tv: String
    ): Call<Tv>

}