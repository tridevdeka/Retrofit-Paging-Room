package com.tridev.retrofitpagingapp.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tridev.retrofitpagingapp.data.Users
import com.tridev.retrofitpagingapp.network.ApiService
import retrofit2.HttpException
import java.io.IOException

class UserPagingSource constructor(private val apiService: ApiService) : PagingSource<Int, Users>() {
    override fun getRefreshKey(state: PagingState<Int, Users>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Users> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getAllDogs(page)
            val users = response.data

            LoadResult.Page(
                data=users,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (users.isEmpty()) null else page + 1
            )
        } catch (
            e: IOException
        ) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}