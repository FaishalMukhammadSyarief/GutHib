package com.zhalz.guthib.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class UserResponse(

    @field:SerializedName("items")
    val items: List<UserData?>? = null

)

@Parcelize
data class UserData(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("login")
    val login: String? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @field:SerializedName("repos_url")
    val reposUrl: String? = null,

): Parcelable