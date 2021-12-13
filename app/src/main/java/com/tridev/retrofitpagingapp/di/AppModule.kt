package com.tridev.retrofitpagingapp.di

import android.app.Application
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tridev.retrofitpagingapp.db.UserDatabase
import com.tridev.retrofitpagingapp.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun providesRetrofit(): ApiService = Retrofit.Builder()
        .baseUrl(ApiService.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(ApiService::class.java)


    @Provides
    @Singleton
    fun providesDatabase(application: Application) =
        Room.databaseBuilder(
            application,
            UserDatabase::class.java,
            "users_db"
        ).build()

    @Provides
    @Singleton
    fun providesDao(db:UserDatabase)=db.userDao()
}