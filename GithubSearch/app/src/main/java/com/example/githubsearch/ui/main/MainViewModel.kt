package com.example.githubsearch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubsearch.api.ResponseSearchUser
import com.example.githubsearch.api.User
import com.example.githubsearch.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel() : ViewModel() {
    private val _listSearch = MutableLiveData<List<User>>()
    val listSearch: LiveData<List<User>> = _listSearch

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<String>()
    val snackbarText: LiveData<String> = _snackbarText

    init {
        searchUser("agus")
    }

    fun searchUser(query: String) {
        _isLoading.value = true

        val client = ApiConfig.getApiService().searchUser(query)
        client.enqueue(object : Callback<ResponseSearchUser> {
            override fun onResponse(
                call: Call<ResponseSearchUser>,
                response: Response<ResponseSearchUser>
            ) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    _listSearch.value = response.body()?.items
                } else {
                    _snackbarText.value = "onFailure: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<ResponseSearchUser>, t: Throwable) {
                _isLoading.value = false
                _snackbarText.value = "onFailure: ${t.message.toString()}"
            }
        })
    }

    companion object {
        private const val TAG = "MainViewModel"
    }

}