package com.example.movieapps.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.movieapps.api.response.ResponseTv
import com.example.movieapps.data.MovieRepositorySecond
import com.example.movieapps.data.local.entity.Tv
import com.example.movieapps.vo.Resource

class TvViewModel(private val movieRepository: MovieRepositorySecond) : ViewModel() {
    fun getTv(): LiveData<Resource<PagedList<Tv>>> = movieRepository.getTvs()
}