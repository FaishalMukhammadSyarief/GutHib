package com.zhalz.guthib.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhalz.guthib.data.response.DetailUser
import com.zhalz.guthib.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {

    val userData = MutableLiveData<DetailUser>()
    val isLoading = MutableLiveData<Boolean>()

    fun getDetailUser(username: String) {

        isLoading.value = true

        val client = ApiConfig.getApiService().getDetail(username)
        client.enqueue(object : Callback<DetailUser> {

            override fun onResponse(call: Call<DetailUser>, response: Response<DetailUser>) {
                if (response.isSuccessful) {
                    userData.value = response.body()
                    isLoading.value = false
                }
            }

            override fun onFailure(call: Call<DetailUser>, t: Throwable) {
                Log.e("DetailViewModel", "onFailure: ${t.message.toString()}")
                isLoading.value = false
            }
        })

    }

}