package com.tridev.retrofitpagingapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Users")
data class UserEntity(


    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "firstName")
    var firstName: String,

    @ColumnInfo(name = "lastName")
    var lastName: String

){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
