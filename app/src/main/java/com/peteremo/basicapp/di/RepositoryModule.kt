package com.peteremo.basicapp.di

import com.peteremo.data.repository.ToDoRepositoryImpl
import com.peteremo.domain.home.ToDoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindToDoRepository(
        impl: ToDoRepositoryImpl
    ): ToDoRepository
}