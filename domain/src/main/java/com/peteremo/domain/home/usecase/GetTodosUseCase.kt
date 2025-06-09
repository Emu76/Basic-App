package com.peteremo.domain.home.usecase

import com.peteremo.domain.home.ToDoRepository
import com.peteremo.domain.home.model.ToDoDomainModel
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(
    private val repository: ToDoRepository
) {
    suspend operator fun invoke(): List<ToDoDomainModel> {
        return repository.getTodos() ?: listOf()
    }
}