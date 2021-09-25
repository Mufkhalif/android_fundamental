package com.example.githubsearch.api
import com.google.gson.annotations.SerializedName

data class ResponseSearchUser(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<User>
)

data class User(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val id: Int,
    val login: String,
    val url: String
)

