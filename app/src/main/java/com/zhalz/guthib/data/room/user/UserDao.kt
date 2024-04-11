package com.zhalz.guthib.data.room.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(username: UserData)

    @Delete
    suspend fun delete(user: UserData)

    @Query("SELECT count(*) FROM UserData WHERE id = :id")
    suspend fun checkFav(id: Int) : Int

    @Query("SELECT * FROM UserData")
    fun getListFav(): Flow<List<UserData>>
}