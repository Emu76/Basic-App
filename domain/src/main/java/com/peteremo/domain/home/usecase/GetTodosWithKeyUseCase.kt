package com.peteremo.domain.home.usecase

import com.peteremo.domain.home.ToDoRepository
import com.peteremo.domain.home.model.ToDoDomainModel
import javax.inject.Inject

private const val DEFAULT_ID = 1
private const val DEFAULT_TITLE = "Title"

class GetTodosWithKeyUseCase @Inject constructor(
    private val repository: ToDoRepository
) {
    suspend operator fun invoke(arg: Arg): ToDoDomainModel? {
        return repository.getTodoByKey(arg.key) ?: ToDoDomainModel(DEFAULT_ID, DEFAULT_TITLE)
    }

    data class Arg(val key: String)
}