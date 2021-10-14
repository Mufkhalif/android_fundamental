package com.example.movieapps.api.response

import com.example.movieapps.data.local.entity.Tv

data class ResponseTv(
    val page: Int,
    val results: List<Tv>,
    val total_results: Int,
)