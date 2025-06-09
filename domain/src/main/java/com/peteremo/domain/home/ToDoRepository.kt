package com.peteremo.domain.home

import com.peteremo.domain.home.model.ToDoDomainModel

interface ToDoRepository {
    suspend fun getTodos(): List<ToDoDomainModel>?
    suspend fun getTodoByKey(key: String): ToDoDomainModel?
}