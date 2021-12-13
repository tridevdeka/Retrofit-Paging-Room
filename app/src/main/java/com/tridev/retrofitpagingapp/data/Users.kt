package com.tridev.retrofitpagingapp.data

import com.squareup.moshi.Json

data class Users(
    @Json(name = "avatar")
    val url: String,

    @Json(name = "first_name")
    val firstName: String,

    @Json(name = "last_name")
    val lastName: String,

    @Json(name = "id")
    val id: String,

    @Json(name = "email")
    val email: String


) {

}
