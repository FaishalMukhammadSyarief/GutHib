package com.zhalz.guthib.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhalz.guthib.data.room.user.UserDao
import com.zhalz.guthib.data.room.user.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val userDao: UserDao): ViewModel() {

    private val _listFav = MutableLiveData<List<UserData>>()
    val listFav: LiveData<List<UserData>> = _listFav

    fun getFavorite() = viewModelScope.launch {
        val listFav = userDao.getFav()
        _listFav.value = listFav
    }

}