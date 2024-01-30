package com.zhalz.guthib.data.retrofit

import com.zhalz.guthib.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    fun searchUser( @Query(value = "q") query: String) : Call<UserResponse>



}