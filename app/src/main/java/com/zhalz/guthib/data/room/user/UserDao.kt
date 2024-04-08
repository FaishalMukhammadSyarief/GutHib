package com.zhalz.guthib.data.room.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserData)

    @Delete
    suspend fun delete(user: UserData)

    @Query("SELECT count(*) FROM UserData WHERE id = :id")
    fun checkFav(id: Int) : Int
}