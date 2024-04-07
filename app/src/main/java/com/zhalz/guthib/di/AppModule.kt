package com.zhalz.guthib.di

import android.content.Context
import com.zhalz.guthib.data.datastore.DataStoreSetting
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context) = DataStoreSetting(context)

}