package com.example.movieapps.ui.detail.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieapps.api.model.Tv
import com.example.movieapps.data.remote.MovieRepositorySecond

class DetailTvViewModel(private val movieRepository: MovieRepositorySecond) : ViewModel() {

    fun getTvDetail(id: String): LiveData<Tv> = movieRepository.getDetailTv(id)

}