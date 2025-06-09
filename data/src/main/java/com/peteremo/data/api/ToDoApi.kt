package com.peteremo.data.api

import com.peteremo.domain.home.model.ToDoDomainModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ToDoApi {
    @GET("/todos/{key}")
    suspend fun getTodo(@Path("key") key: String): Response<ToDoDomainModel>

    @GET("/todos")
    suspend fun getAllTodos(): Response<List<ToDoDomainModel>>
}