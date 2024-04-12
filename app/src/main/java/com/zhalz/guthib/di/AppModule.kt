package com.zhalz.guthib.di

import android.content.Context
import com.zhalz.guthib.BuildConfig
import com.zhalz.guthib.data.datastore.DataStoreSetting
import com.zhalz.guthib.data.retrofit.ApiService
import com.zhalz.guthib.data.retrofit.interceptor.AuthInterceptor
import com.zhalz.guthib.data.room.AppDatabase
import com.zhalz.guthib.utils.Const.Api.API_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context) = DataStoreSetting(context)

    @Singleton
    @Provides
    fun provideUserDao(@ApplicationContext context: Context) = AppDatabase.getInstance(context).userDao()

    @Provides
    fun provideApiService(): ApiService {
        val loggingInterceptor =
            if(BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)

        val client =
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(AuthInterceptor(API_KEY))
                .build()

        val retrofit =
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        return retrofit.create(ApiService::class.java)
    }

}