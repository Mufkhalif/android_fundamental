package com.example.movieapps.api

import com.example.movieapps.api.model.Movie
import com.example.movieapps.api.model.Tv
import com.example.movieapps.api.response.ResponseMovie
import com.example.movieapps.api.response.ResponseTv
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/popular?api_key=${Constants.baseUrl}")
    fun getMovie(): Call<ResponseMovie>

    @GET("tv/popular?api_key=${Constants.baseUrl}")
    fun getTv(): Call<ResponseTv>

    @GET("movie/{movie}?api_key=${Constants.baseUrl}")
    fun getDetailMovie(
        @Path("movie") movie: String
    ): Call<Movie>

    @GET("tv/{tv}?api_key=${Constants.baseUrl}")
    fun getDetailTv(
        @Path("tv") tv: String
    ): Call<Tv>

}