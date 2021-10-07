package com.example.movieapps.api.model

data class Movie(
    var id: Int,
    var title: String,
    var overview: String,
    var release_date: String,
    var popularity: Double,
    var vote_average: Double,
    var vote_count: Int,
    var poster_path: String,
)