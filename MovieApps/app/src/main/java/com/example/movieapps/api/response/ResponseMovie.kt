package com.example.movieapps.api.response

import com.example.movieapps.api.model.Movie

data class ResponseMovie(
    var page: Int,
    val results: List<Movie>,
    val total_pages: Int,
)