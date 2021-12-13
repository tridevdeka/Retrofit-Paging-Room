package com.tridev.retrofitpagingapp.network

import com.tridev.retrofitpagingapp.data.Users
import com.tridev.retrofitpagingapp.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val BASE_URL = "https://reqres.in/"
    }

    @GET("api/users")
    suspend fun getAllDogs(
        @Query("page") page: Int,
    ):UserResponse


}