package com.example.movieapps.ui.detail.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.movieapps.data.local.entity.Tv
import com.example.movieapps.data.MovieRepositorySecond
import com.example.movieapps.data.local.entity.Movie
import com.example.movieapps.vo.Resource

class DetailTvViewModel(private val movieRepository: MovieRepositorySecond) : ViewModel() {
    private val tvId = MutableLiveData<String>()

    fun setSelectedTv(tvId: String) {
        this.tvId.value = tvId
    }

    var tv: LiveData<Resource<Tv>> = Transformations.switchMap(tvId) { tvId ->
        movieRepository.getDetailTv(tvId)
    }

    fun setBookmark() {
        val tvResource = tv.value
        if (tvResource != null) {
            val tvData = tvResource.data
            if (tvData != null) {
                movieRepository.setTvBookmark(tvData, !tvData.bookmarked)
            }
        }
    }

}