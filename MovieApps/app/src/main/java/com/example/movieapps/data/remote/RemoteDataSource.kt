package com.example.movieapps.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieapps.api.ApiConfig
import com.example.movieapps.data.local.entity.Movie
import com.example.movieapps.data.local.entity.Tv
import com.example.movieapps.api.response.ResponseMovie
import com.example.movieapps.api.response.ResponseTv
import com.example.movieapps.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        private val api = ApiConfig().getApiService()
    }

    fun getMovies(): LiveData<ApiResponse<ResponseMovie>> {
        EspressoIdlingResource.increment()

        val resultMovie = MutableLiveData<ApiResponse<ResponseMovie>>()
        api.getMovie().enqueue(object : Callback<ResponseMovie> {
            override fun onResponse(call: Call<ResponseMovie>, response: Response<ResponseMovie>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        resultMovie.value = ApiResponse.success(body)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
                resultMovie.value = ApiResponse.error(
                    "onFailure: ${t.message.toString()}",
                    ResponseMovie(0, ArrayList(), 0)
                )
            }
        })

        EspressoIdlingResource.decrement()
        return resultMovie
    }

    fun getTvs(): LiveData<ApiResponse<ResponseTv>> {
        EspressoIdlingResource.increment()

        val resultTv = MutableLiveData<ApiResponse<ResponseTv>>()
        api.getTv().enqueue(object : Callback<ResponseTv> {
            override fun onResponse(call: Call<ResponseTv>, response: Response<ResponseTv>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        resultTv.value = ApiResponse.success(body)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseTv>, t: Throwable) {
                resultTv.value = ApiResponse.error(
                    "onFailure: ${t.message.toString()}",
                    ResponseTv(0, ArrayList(), 0)
                )
            }
        })

        EspressoIdlingResource.decrement()
        return resultTv
    }

    fun getDetailMovie(id: String): LiveData<ApiResponse<Movie>> {
        EspressoIdlingResource.increment()

        val resultMovie = MutableLiveData<ApiResponse<Movie>>()

        api.getDetailMovie(id).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let { resultMovie.value = ApiResponse.success(body) }
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                resultMovie.value = ApiResponse.error(
                    "onFailure: ${t.message.toString()}",
                    Movie(
                        0,
                        "",
                        "",
                        "",
                        0.0,
                        0.0,
                        0,
                        "",
                        false
                    )
                )
            }
        })

        EspressoIdlingResource.decrement()
        return resultMovie
    }

    fun getDetailTv(id: String): LiveData<ApiResponse<Tv>> {
        EspressoIdlingResource.increment()

        val resultTv = MutableLiveData<ApiResponse<Tv>>()
        api.getDetailTv(id).enqueue(object : Callback<Tv> {
            override fun onResponse(call: Call<Tv>, response: Response<Tv>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        resultTv.value = ApiResponse.success(body)
                    }
                }
            }

            override fun onFailure(call: Call<Tv>, t: Throwable) {
                resultTv.value = ApiResponse.error(
                    "onFailure: ${t.message.toString()}",
                    Tv(
                        0,
                        "",
                        "",
                        "",
                        0.0,
                        0.0,
                        0,
                        "",
                        false
                    )
                )
            }
        })

        EspressoIdlingResource.decrement()
        return resultTv
    }
}