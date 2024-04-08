package com.zhalz.guthib.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhalz.guthib.data.response.DetailUser
import com.zhalz.guthib.data.retrofit.ApiConfig
import com.zhalz.guthib.data.room.user.UserDao
import com.zhalz.guthib.data.room.user.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val userDao: UserDao): ViewModel() {

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

    fun insertUser(user: UserData) = viewModelScope.launch {
        userDao.insert(user)
    }

    fun checkFav(id: Int): Boolean {
        val count = userDao.checkFav(id)
        return count > 0
    }

}