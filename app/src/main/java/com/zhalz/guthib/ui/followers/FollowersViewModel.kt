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

    fun getFollowers(username: String) {

        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object: Callback<List<UserData>> {

            override fun onResponse(call: Call<List<UserData>>, response: Response<List<UserData>>) {
                if (response.isSuccessful) _listFollowers.value = response.body()
                else Log.e("MainViewModel", "onFailure: ${response.message()}")
            }

            override fun onFailure(call: Call<List<UserData>>, t: Throwable) {
                Log.e("MainViewModel", "onFailure: ${t.message.toString()}")
            }

        })

    }

}