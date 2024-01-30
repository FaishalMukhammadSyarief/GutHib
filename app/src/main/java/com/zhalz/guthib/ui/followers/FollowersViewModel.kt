package com.zhalz.guthib.ui.followers

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhalz.guthib.data.response.UserData
import com.zhalz.guthib.data.response.UserResponse
import com.zhalz.guthib.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel: ViewModel() {

    private val _listFollowers = MutableLiveData<List<UserData?>?>()
    val  listFollowers = _listFollowers

    fun getFollowers(linkFollowers: String) {

        val client = ApiConfig.getApiService().getFollowers(linkFollowers)
        client.enqueue(object: Callback<UserResponse> {

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) _listFollowers.value = response.body()?.items
                else Log.e("MainViewModel", "onFailure: ${response.message()}")
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("MainViewModel", "onFailure: ${t.message.toString()}")
            }

        })

    }

}