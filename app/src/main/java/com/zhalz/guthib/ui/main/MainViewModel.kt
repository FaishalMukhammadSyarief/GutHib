package com.zhalz.guthib.ui.main

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

    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()
    val isEmpty = MutableLiveData<Boolean>()

    fun getUser(query: String) {

        isLoading.value = true
        isError.value = false
        isEmpty.value = false

        val client = ApiConfig.getApiService().searchUser(query)
        client.enqueue(object : Callback<UserResponse> {

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    _listUser.value = response.body()?.items
                    isLoading.value = false
                    if (response.body()?.totalCount == 0) {
                        isEmpty.value = true
                    }
                }
                else {
                    Log.e("MainViewModel", "onFailure: ${response.message()}")
                    isError.value = true
                    isLoading.value = false
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("MainViewModel", "onFailure: ${t.message.toString()}")
                isError.value = true
                isLoading.value = false
            }

        })

    }

}