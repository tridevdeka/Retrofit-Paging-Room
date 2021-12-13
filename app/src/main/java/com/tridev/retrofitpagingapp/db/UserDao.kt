package com.tridev.retrofitpagingapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY id DESC")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUsers(userEntity: UserEntity)

    @Query("DELETE FROM Users WHERE id =:id")
    suspend fun deleteSpecificUser(id: Int)

    @Update
    suspend fun updateUsers(userEntity: UserEntity)

    @Delete
    suspend fun deleteUsers(userEntity: UserEntity)


}