package com.zhalz.guthib.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.zhalz.guthib.data.constant.Const.DataStore.DATA_STORE_NAME
import com.zhalz.guthib.data.constant.Const.DataStore.THEME_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreSetting(context: Context) {

    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)
    private val dataStore = context.datastore

    fun getTheme(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[themeKey] ?: false
        }
    }

    suspend fun setTheme(isDark: Boolean) {
        dataStore.edit { preferences ->
            preferences[themeKey] = isDark
        }
    }

    companion object {
        private val themeKey = booleanPreferencesKey(THEME_KEY)
    }

}