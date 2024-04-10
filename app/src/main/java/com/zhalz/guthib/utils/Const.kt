package com.zhalz.guthib.utils

import com.zhalz.guthib.BuildConfig

object Const {

    object Parcel {
        const val EXTRA_USER = "extra_user"
    }

    object DataStore {
        const val DATA_STORE_NAME = "settings"
        const val THEME_KEY = "theme_settings"
    }

    object Api {
        const val API_KEY = BuildConfig.GITHUB_API_KEY
    }

}