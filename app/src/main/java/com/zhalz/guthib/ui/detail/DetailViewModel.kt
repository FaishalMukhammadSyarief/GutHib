package com.zhalz.guthib.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhalz.guthib.data.retrofit.response.DetailUser
import com.zhalz.guthib.data.retrofit.ApiService
import com.zhalz.guthib.data.room.user.UserDao
import com.zhalz.guthib.data.room.user.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val apiService: ApiService, private val userDao: UserDao): ViewModel() {

    private val _isFav = MutableLiveData<Boolean>()
    val isFav : LiveData<Boolean> = _isFav

    val detailUser = MutableLiveData<DetailUser>()
    val isLoading = MutableLiveData<Boolean>()

    fun getDetailUser(username: String) {

        isLoading.value = true

        val client = apiService.getDetail(username)
        client.enqueue(object : Callback<DetailUser> {

            override fun onResponse(call: Call<DetailUser>, response: Response<DetailUser>) {
                if (response.isSuccessful) {
                    detailUser.value = response.body()
                    isLoading.value = false
                }
            }

            override fun onFailure(call: Call<DetailUser>, t: Throwable) {
                Log.e("DetailViewModel", "onFailure: ${t.message.toString()}")
                isLoading.value = false
            }
        })

    }

    fun checkFav(id: Int) = viewModelScope.launch {
        val count = userDao.checkFav(id)
        _isFav.value = count > 0
    }

    fun insertUser(user: UserData) = viewModelScope.launch {
        userDao.insert(user)
        _isFav.value = true
    }

    fun deleteUser(user: UserData) = viewModelScope.launch {
        userDao.delete(user)
        _isFav.value = false
    }

}