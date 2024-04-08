package com.zhalz.guthib.data.room.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: UserData)

    @Delete
    suspend fun delete(user: UserData)
}