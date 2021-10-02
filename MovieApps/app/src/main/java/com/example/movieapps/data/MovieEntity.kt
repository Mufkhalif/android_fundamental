package com.example.movieapps.data

data class MovieEntity(
    var id: String,
    var title: String,
    var overview: String,
    var releaseDate: String,
    var popularity: Double,
    var voteAverage: Int,
    var voteCount: Int,
    var posterPath: String
)

