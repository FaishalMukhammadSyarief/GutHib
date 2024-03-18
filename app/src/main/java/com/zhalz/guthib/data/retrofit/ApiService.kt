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
    @Headers("Authorization: $API_KEY")
    fun searchUser( @Query(value = "q") query: String) : Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: $API_KEY")
    fun getDetail( @Path("username") username: String ) : Call<DetailUser>

    @GET("users/{username}/followers")
    @Headers("Authorization: $API_KEY")
    fun getFollowers( @Path("username") username: String ) : Call<List<UserData>>

    @GET("users/{username}/following")
    @Headers("Authorization: $API_KEY")
    fun getFollowing( @Path("username") username: String ) : Call<List<UserData>>

    companion object {
        const val API_KEY = BuildConfig.GITHUB_API_KEY
    }

}