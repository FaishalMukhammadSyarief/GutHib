package com.zhalz.guthib.data.retrofit

import com.zhalz.guthib.data.response.DetailUser
import com.zhalz.guthib.data.response.UserData
import com.zhalz.guthib.data.response.UserResponse
import retrofit2.Call
import com.zhalz.guthib.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("search/users")
    @Headers("Authorization: $apiKey")
    fun searchUser( @Query(value = "q") query: String) : Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: $apiKey")
    fun getDetail( @Path("username") username: String ) : Call<DetailUser>

    @GET("users/{username}/followers")
    @Headers("Authorization: $apiKey")
    fun getFollowers( @Path("username") username: String ) : Call<List<UserData>>

    @GET("users/{username}/following")
    @Headers("Authorization: $apiKey")
    fun getFollowing( @Path("username") username: String ) : Call<List<UserData>>

    companion object {
        const val apiKey = BuildConfig.GITHUB_API_KEY
    }

}