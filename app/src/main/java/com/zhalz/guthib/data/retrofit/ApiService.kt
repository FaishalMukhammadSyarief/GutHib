package com.zhalz.guthib.data.retrofit

import com.zhalz.guthib.data.retrofit.response.DetailUser
import com.zhalz.guthib.data.retrofit.response.UserResponse
import retrofit2.Call
import com.zhalz.guthib.data.room.user.UserData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    fun searchUser( @Query(value = "q") query: String) : Call<UserResponse>

    @GET("users/{username}")
    fun getDetail( @Path("username") username: String ) : Call<DetailUser>

    @GET("users/{username}/followers")
    fun getFollowers( @Path("username") username: String ) : Call<List<UserData>>

    @GET("users/{username}/following")
    fun getFollowing( @Path("username") username: String ) : Call<List<UserData>>

}