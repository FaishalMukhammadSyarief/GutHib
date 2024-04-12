package com.zhalz.guthib.ui.followers

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhalz.guthib.data.retrofit.ApiService
import com.zhalz.guthib.data.room.user.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel @Inject constructor(private val apiService: ApiService): ViewModel() {

    private val _listFollowers = MutableLiveData<List<UserData?>?>()
    val  listFollowers = _listFollowers

    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()
    val isEmpty = MutableLiveData<Boolean>()

    fun getFollowers(username: String) {

        isLoading.value = true
        isError.value = false
        isEmpty.value = false

        val client = apiService.getFollowers(username)
        client.enqueue(object: Callback<List<UserData>> {

            override fun onResponse(call: Call<List<UserData>>, response: Response<List<UserData>>) {
                if (response.isSuccessful) {
                    _listFollowers.value = response.body()
                    isLoading.value = false
                    val totalCount = response.body()?.size
                    if (totalCount == 0) isEmpty.value = true
                }
                else {
                    Log.e("FollowersViewModel", "onFailure: ${response.message()}")
                    isLoading.value = false
                    isError.value = true
                }
            }

            override fun onFailure(call: Call<List<UserData>>, t: Throwable) {
                Log.e("FollowersViewModel", "onFailure: ${t.message.toString()}")
                isLoading.value = false
                isError.value = true
            }
        })

    }

}