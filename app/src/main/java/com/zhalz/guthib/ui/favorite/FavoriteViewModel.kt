package com.zhalz.guthib.ui.favorite

import androidx.lifecycle.ViewModel
import com.zhalz.guthib.data.room.user.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val userDao: UserDao) : ViewModel() {

    fun getListFav() = userDao.getListFav()

}