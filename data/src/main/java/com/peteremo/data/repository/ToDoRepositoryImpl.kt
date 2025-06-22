package com.peteremo.data.repository

import com.peteremo.data.api.ToDoApi
import com.peteremo.data.di.IoDispatcher
import com.peteremo.data.extensions.NetworkExtensions.check
import com.peteremo.domain.home.ToDoRepository
import com.peteremo.domain.home.model.ToDoDomainModel
import dagger.Reusable
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@Reusable
class ToDoRepositoryImpl @Inject constructor(
    private val api: ToDoApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ToDoRepository {
    override suspend fun getTodos(): List<ToDoDomainModel>? {
        with(ioDispatcher) {
            return api.getAllTodos().check()
        }
    }

    override suspend fun getTodoByKey(key: String): ToDoDomainModel? {
        with(ioDispatcher) {
            return api.getTodo(key).check()
        }
    }
}
