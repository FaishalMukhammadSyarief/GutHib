package com.zhalz.guthib.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.zhalz.guthib.data.datastore.DataStoreSetting
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val dataStore: DataStoreSetting) : ViewModel() {

    fun setTheme(isDark: Boolean) = viewModelScope.launch {
        dataStore.setTheme(isDark)
    }

    fun getTheme() = dataStore.getTheme().asLiveData(Dispatchers.IO)

}