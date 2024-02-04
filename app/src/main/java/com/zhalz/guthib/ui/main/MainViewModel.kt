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
    val isLoadingGone = MutableLiveData<Boolean>()
    val isSearching = MutableLiveData<Boolean>()
    val isErrorGone = MutableLiveData<Boolean>()

    fun getUser(query: String) {

        isLoadingGone.value = false
        isSearching.value = true
        isErrorGone.value = true

        val client = ApiConfig.getApiService().searchUser(query)
        client.enqueue(object : Callback<UserResponse> {

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    _listUser.value = response.body()?.items
                    isLoadingGone.value = true
                    if (response.body()?.totalCount == 0) {
                        isSearching.value = false
                    }
                }
                else {
                    Log.e("MainViewModel", "onFailure: ${response.message()}")
                    isErrorGone.value = false
                    isLoadingGone.value = true
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("MainViewModel", "onFailure: ${t.message.toString()}")
                isErrorGone.value = false
                isLoadingGone.value = true
            }

        })

    }

}