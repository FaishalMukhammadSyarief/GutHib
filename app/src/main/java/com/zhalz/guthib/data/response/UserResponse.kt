package com.zhalz.guthib.data.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @field:SerializedName("items")
    val items: List<UserData?>? = null

)

data class UserData(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("login")
    val login: String? = null,

    @field:SerializedName("followers_url")
    val followersUrl: String? = null,

    @field:SerializedName("following_url")
    val followingUrl: String? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("repos_url")
    val reposUrl: String? = null,

    @field:SerializedName("url")
    val url: String? = null

)