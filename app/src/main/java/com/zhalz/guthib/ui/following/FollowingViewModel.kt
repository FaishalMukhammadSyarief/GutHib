package com.zhalz.guthib.ui.following

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhalz.guthib.data.room.user.UserData
import com.zhalz.guthib.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel: ViewModel() {

    private val _listFollowing = MutableLiveData<List<UserData?>?>()
    val listFollowing = _listFollowing
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()
    val isEmpty = MutableLiveData<Boolean>()

    fun getFollowing(username:String) {

        isLoading.value = true
        isError.value = false
        isEmpty.value = false

        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object: Callback<List<UserData>> {

            override fun onResponse(call: Call<List<UserData>>, response: Response<List<UserData>>) {
                if (response.isSuccessful) {
                    _listFollowing.value = response.body()
                    isLoading.value = false
                    val totalCount = response.body()?.size
                    if (totalCount == 0) isEmpty.value = true
                }
                else {
                    Log.e("FollowingViewModel", "onFailure: ${response.message()}")
                    isLoading.value = false
                    isError.value = true
                }
            }

            override fun onFailure(call: Call<List<UserData>>, t: Throwable) {
                Log.e("FollowingViewModel", "onFailure: ${t.message.toString()}")
                isLoading.value = false
                isError.value = true
            }
        })

    }

}