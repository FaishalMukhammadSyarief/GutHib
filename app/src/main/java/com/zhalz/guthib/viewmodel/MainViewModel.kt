package com.zhalz.guthib.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhalz.guthib.data.response.UserData
import com.zhalz.guthib.data.response.UserResponse
import com.zhalz.guthib.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _listUser = MutableLiveData<List<UserData?>?>()
    val listUser = _listUser

    init { getUserList() }

    private fun getUserList() {

        val client = ApiConfig.getApiService().searchUser()
        client.enqueue(object : Callback<UserResponse> {

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) _listUser.value = response.body()?.items
                else Log.e("MainViewModel", "onFailure: ${response.message()}")
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("MainViewModel", "onFailure: ${t.message.toString()}")
            }

        })

    }

}