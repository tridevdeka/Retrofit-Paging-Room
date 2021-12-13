package com.tridev.retrofitpagingapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.tridev.retrofitpagingapp.db.UserEntity
import com.tridev.retrofitpagingapp.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalViewModel @Inject constructor(private val localRepository: LocalRepository) :
    ViewModel() {

    val users = localRepository.getAllUsers().asLiveData()

    fun insertUsers(userEntity: UserEntity) = viewModelScope.launch {
        localRepository.insertUsers(userEntity)
    }

    fun deleteUsers(userEntity: UserEntity) = viewModelScope.launch {
        localRepository.deleteUsers(userEntity)
    }

    fun updateUsers(userEntity: UserEntity) = viewModelScope.launch {
        localRepository.updateUsers(userEntity)
    }
}