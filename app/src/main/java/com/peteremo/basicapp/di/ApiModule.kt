package com.peteremo.basicapp.di

import com.peteremo.basicapp.util.Urls.TODO_API_URL
import com.peteremo.data.api.ToDoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun providesToDoApi(): ToDoApi = Retrofit.Builder()
        .baseUrl(TODO_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ToDoApi::class.java)
}