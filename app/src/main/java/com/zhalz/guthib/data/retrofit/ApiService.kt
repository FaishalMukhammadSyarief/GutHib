package com.zhalz.guthib.data.retrofit

import com.zhalz.guthib.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("search/users?q=zhalz")
    fun searchUser() : Call<UserResponse>

}