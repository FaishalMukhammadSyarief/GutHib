package com.zhalz.guthib.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zhalz.guthib.data.room.user.UserDao
import com.zhalz.guthib.data.room.user.UserData

@Database(
    entities = [UserData::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, context.packageName)
                .fallbackToDestructiveMigration()
                .build()

            INSTANCE = instance
            return instance

        }
    }

}