package com.zhalz.guthib.data.response

import com.google.gson.annotations.SerializedName
import com.zhalz.guthib.data.room.user.UserData

data class UserResponse(

    @field:SerializedName("total_count")
    val totalCount: Int? = null,

    @field:SerializedName("items")
    val items: List<UserData?>? = null

)

data class DetailUser (

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("login")
    val login: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("bio")
    val bio: String? = null,

    @field:SerializedName("followers")
    val followers: Int? = null,

    @field:SerializedName("following")
    val following: Int? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

)