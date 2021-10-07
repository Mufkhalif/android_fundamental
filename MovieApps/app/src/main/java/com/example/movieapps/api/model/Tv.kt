package com.example.movieapps.api.model

data class Tv(
    val id: Int,
    val name: String,
    val overview: String,
    val first_air_date: String,
    val popularity: Double,
    val vote_average: Double,
    val vote_count: Int,
    val poster_path: String,
)