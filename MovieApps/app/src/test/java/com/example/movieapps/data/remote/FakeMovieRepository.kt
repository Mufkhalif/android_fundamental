package com.example.movieapps.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapps.api.model.Movie
import com.example.movieapps.api.model.Tv
import com.example.movieapps.api.response.ResponseMovie
import com.example.movieapps.api.response.ResponseTv

class FakeMovieRepository(private val remoteDataSource: RemoteDataSource) : MovieDataSource {

    override fun getMovies(): LiveData<ResponseMovie> {
        val moviesResult = MutableLiveData<ResponseMovie>()

        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(moviesResponses: ResponseMovie) {
                moviesResult.postValue(moviesResponses)
            }
        })

        return moviesResult
    }

    override fun getTvs(): LiveData<ResponseTv> {
        val tvResult = MutableLiveData<ResponseTv>()

        remoteDataSource.getTvs(object : RemoteDataSource.LoadTvCallback {
            override fun onAllTvReceived(tvResponse: ResponseTv) {
                tvResult.postValue(tvResponse)
            }
        })

        return tvResult
    }

    override fun getDetailMovie(id: String): LiveData<Movie> {
        val moviesResult = MutableLiveData<Movie>()

        remoteDataSource.getDetailMovie(id, object : RemoteDataSource.LoadDetailMovieCallback {
            override fun onDetailMovieReceived(detailMovieResponse: Movie) {
                moviesResult.postValue(detailMovieResponse)
            }

        })
        return moviesResult
    }

    override fun getDetailTv(id: String): LiveData<Tv> {
        val tvResult = MutableLiveData<Tv>()

        remoteDataSource.getDetailTv(id, object : RemoteDataSource.LoadDetailTvCallback {
            override fun onDetailTvCallback(detailTvResponse: Tv) {
                tvResult.postValue(detailTvResponse)
            }
        })

        return tvResult
    }

}