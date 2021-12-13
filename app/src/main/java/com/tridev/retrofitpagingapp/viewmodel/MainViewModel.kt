package com.tridev.retrofitpagingapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tridev.retrofitpagingapp.data.Users
import com.tridev.retrofitpagingapp.network.ApiService
import com.tridev.retrofitpagingapp.repository.UserPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(private val apiService: ApiService) : ViewModel() {

    val getAllUsers: Flow<PagingData<Users>> = Pager(
        config = PagingConfig(6, enablePlaceholders = false)
    ) {
        UserPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)
}
