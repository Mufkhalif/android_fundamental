package com.example.movieapps.data

data class TvEntity(
	var id: String,
	var name: String,
	var overview: String,
	var firstAirDate: String,
	var posterPath: String,
	var popularity: Double,
	var voteAverage: Double,
	var voteCount: Int
)
