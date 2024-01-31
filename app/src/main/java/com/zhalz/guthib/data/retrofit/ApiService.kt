package com.zhalz.guthib.data.retrofit

import com.zhalz.guthib.data.response.DetailUser
import com.zhalz.guthib.data.response.UserData
import com.zhalz.guthib.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun searchUser( @Query(value = "q") query: String) : Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetail( @Path("username") username: String ) : Call<DetailUser>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers( @Path("username") username: String ) : Call<List<UserData>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing( @Path("username") username: String ) : Call<List<UserData>>

}