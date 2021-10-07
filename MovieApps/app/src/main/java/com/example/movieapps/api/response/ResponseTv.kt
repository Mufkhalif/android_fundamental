package com.example.movieapps.api.response

import com.example.movieapps.api.model.Status
import com.example.movieapps.api.model.Tv

data class ResponseTv(
    val page: Int,
    val results: List<Tv>,
    val total_results: Int,
    val success: Status
)