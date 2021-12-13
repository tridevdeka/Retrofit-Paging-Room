package com.tridev.retrofitpagingapp.repository

import androidx.lifecycle.LiveData
import com.tridev.retrofitpagingapp.db.UserDao
import com.tridev.retrofitpagingapp.db.UserEntity
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val userDao: UserDao
) {

    suspend fun insertUsers(userEntity: UserEntity) {
        userDao.addUsers(userEntity)
    }

    suspend fun deleteUsers(userEntity: UserEntity) {
        userDao.deleteUsers(userEntity)
    }

    fun getAllUsers() = userDao.getAllUsers()

    suspend fun updateUsers(userEntity: UserEntity) {
        userDao.updateUsers(userEntity)
    }
}