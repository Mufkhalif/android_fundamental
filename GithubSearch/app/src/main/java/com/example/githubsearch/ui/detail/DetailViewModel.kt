package com.example.githubsearch.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubsearch.api.ResponseDetail
import com.example.githubsearch.api.User
import com.example.githubsearch.api.ApiConfig
import com.example.githubsearch.database.UserFavourite
import com.example.githubsearch.database.UserFavouriteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application) : ViewModel() {

    companion object {
        private const val TAG = "DetailViewModel"
    }

    private val _detailUser = MutableLiveData<ResponseDetail>()
    val detailUser: LiveData<ResponseDetail> = _detailUser

    private val _snackbarText = MutableLiveData<String>()
    val snackbarText: LiveData<String> = _snackbarText

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _followers = MutableLiveData<List<User>>()
    val followers: LiveData<List<User>> = _followers

    private val _following = MutableLiveData<List<User>>()
    val following: LiveData<List<User>> = _following

    private val _isLoadingFollowing = MutableLiveData<Boolean>()
    val isLoadingFollowing: LiveData<Boolean> = _isLoadingFollowing

    private val _isLoadingFollowers = MutableLiveData<Boolean>()
    val isLoadingFollowers: LiveData<Boolean> = _isLoadingFollowers

    private val _isFav = MutableLiveData<Boolean>()
    val isFav: LiveData<Boolean> = _isFav

    private val _isUserFavourite = MutableLiveData<UserFavourite>()
    val isUserFavourite: LiveData<UserFavourite> = _isUserFavourite

    private val mUserFavouriteRepository: UserFavouriteRepository =
        UserFavouriteRepository(application)

    fun insert(user: UserFavourite) {
        mUserFavouriteRepository.insert(user)
    }

    fun delete(user: UserFavourite) {
        mUserFavouriteRepository.delete(user)
    }

    fun setIsFav(value: Boolean) {
        _isFav.value = value
    }

    fun setDataUserNow(user: UserFavourite) {
        _isUserFavourite.value = user
    }

    fun checkIsFavourite(username: String?): LiveData<List<UserFavourite>> {
        return mUserFavouriteRepository.getUserFavouriteByUsername(username)
    }

    fun setDetailUser(username: String) {
        getDetailUser(username)
        getUserFollowers(username)
        getUserFollowing(username)
    }

    private fun getDetailUser(username: String) {
        _isLoading.value = true

        val client = ApiConfig.getApiService().detailUser(username)
        client.enqueue(object : Callback<ResponseDetail> {
            override fun onResponse(
                call: Call<ResponseDetail>,
                response: Response<ResponseDetail>
            ) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    _detailUser.value = response.body()
                } else {
                    _snackbarText.value = "onFailure: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<ResponseDetail>, t: Throwable) {
                _isLoading.value = false
                _snackbarText.value = "onFailure: ${t.message.toString()}"
            }

        })

    }

    private fun getUserFollowers(username: String) {
        _isLoadingFollowers.value = true

        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<List<User>> {
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                _isLoadingFollowers.value = false

                if (response.isSuccessful) {
                    _followers.value = response.body()
                } else {
                    _snackbarText.value = "onFailure: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                _isLoadingFollowers.value = false
                _snackbarText.value = "onFailure: ${t.message.toString()}"
            }
        })

    }

    private fun getUserFollowing(username: String) {
        _isLoadingFollowing.value = true

        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<User>> {
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                _isLoadingFollowing.value = false

                if (response.isSuccessful) {
                    _following.value = response.body()
                } else {
                    _snackbarText.value = "onFailure: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                _isLoadingFollowing.value = true
                _snackbarText.value = "onFailure: ${t.message.toString()}"
            }

        })
    }
}