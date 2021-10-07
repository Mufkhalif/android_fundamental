package com.example.movieapps.data.remote

import android.util.Log
import com.example.movieapps.api.ApiConfig
import com.example.movieapps.api.model.Movie
import com.example.movieapps.api.model.Tv
import com.example.movieapps.api.response.ResponseMovie
import com.example.movieapps.api.response.ResponseTv
import com.example.movieapps.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiConfig: ApiConfig) {

    companion object {
        private const val TAG = "RemoteDataSource"
        private val api = ApiConfig().getApiService()

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiConfig: ApiConfig): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource(apiConfig).apply { instance = this }
            }
    }

    fun getMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()

        val client = api.getMovie()
        client.enqueue(object : Callback<ResponseMovie> {
            override fun onResponse(call: Call<ResponseMovie>, response: Response<ResponseMovie>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        callback.onAllMoviesReceived(body)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        EspressoIdlingResource.decrement()
    }

    fun getTvs(callback: LoadTvCallback) {
        EspressoIdlingResource.increment()

        val client = api.getTv()
        client.enqueue(object : Callback<ResponseTv> {
            override fun onResponse(call: Call<ResponseTv>, response: Response<ResponseTv>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        callback.onAllTvReceived(body)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseTv>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        EspressoIdlingResource.decrement()
    }

    fun getDetailMovie(id: String, callback: LoadDetailMovieCallback) {
        EspressoIdlingResource.increment()

        val client = api.getDetailMovie(id)
        client.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        callback.onDetailMovieReceived(body)
                    }
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }

        })

        EspressoIdlingResource.decrement()
    }

    fun getDetailTv(id: String, callback: LoadDetailTvCallback) {
        EspressoIdlingResource.increment()

        val client = api.getDetailTv(id)
        client.enqueue(object : Callback<Tv> {
            override fun onResponse(call: Call<Tv>, response: Response<Tv>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        callback.onDetailTvCallback(body)
                    }
                }
            }

            override fun onFailure(call: Call<Tv>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }

        })
        EspressoIdlingResource.decrement()
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(moviesResponses: ResponseMovie)
    }

    interface LoadTvCallback {
        fun onAllTvReceived(tvResponse: ResponseTv)
    }

    interface LoadDetailMovieCallback {
        fun onDetailMovieReceived(detailMovieResponse: Movie);
    }

    interface LoadDetailTvCallback {
        fun onDetailTvCallback(detailTvResponse: Tv)
    }


}