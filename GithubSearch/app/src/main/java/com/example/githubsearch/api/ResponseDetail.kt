package com.example.githubsearch.api

import com.google.gson.annotations.SerializedName

data class ResponseDetail(

	@field:SerializedName("followers")
	val followers: Int,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("following")
	val following: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("company")
	val company: String,

	@field:SerializedName("location")
	val location: String,

	@field:SerializedName("public_repos")
	val publicRepos: Int,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("public_gists")
	val publicGists: Int,

	@field:SerializedName("url")
	val url: String
)
