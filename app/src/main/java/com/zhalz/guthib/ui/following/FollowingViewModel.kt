package com.zhalz.guthib.ui.following

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhalz.guthib.data.response.UserData
import com.zhalz.guthib.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel: ViewModel() {

    private val _listFollowing = MutableLiveData<List<UserData?>?>()
    val listFollowing = _listFollowing

    fun getFollowing(username:String) {

        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<UserData>> {
            override fun onResponse(
                call: Call<List<UserData>>,
                response: Response<List<UserData>>
            ) {
                if (response.isSuccessful) _listFollowing.value = response.body()
                else Log.e("FollowingViewModel", "onFailure: ${response.message()}")
            }

            override fun onFailure(call: Call<List<UserData>>, t: Throwable) {
                Log.e("FollowingViewModel", "onFailure: ${t.message.toString()}")
            }
        })

    }

}