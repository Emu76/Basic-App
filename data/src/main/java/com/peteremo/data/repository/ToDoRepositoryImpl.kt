package com.peteremo.data.repository

import com.peteremo.data.api.ToDoApi
import com.peteremo.data.extensions.NetworkExtensions.check
import com.peteremo.domain.home.ToDoRepository
import com.peteremo.domain.home.model.ToDoDomainModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class ToDoRepositoryImpl @Inject constructor(
    private val api: ToDoApi
) : ToDoRepository {
    override suspend fun getTodos(): List<ToDoDomainModel>? {
        return api.getAllTodos().check()
    }

    override suspend fun getTodoByKey(key: String): ToDoDomainModel? {
        return api.getTodo(key).check()
    }
}