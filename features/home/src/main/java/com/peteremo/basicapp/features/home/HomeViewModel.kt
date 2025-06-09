package com.peteremo.basicapp.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peteremo.basicapp.features.home.helper.HomeHelper
import com.peteremo.domain.home.usecase.GetTodosUseCase
import com.peteremo.domain.home.usecase.GetTodosWithKeyUseCase
import com.peteremo.features.home.model.HomePresentationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase,
    private val getTodoWithKeyUseCase: GetTodosWithKeyUseCase,
    private val homeHelper: HomeHelper
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomePresentationModel?>(null)
    val uiState get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.emit(
                HomePresentationModel(
                    homeHelper.getTodoKey(),
                    homeHelper.getStartTitle()
                )
            )
            updateState()
        }
    }

    private suspend fun updateState() {
        delay(1000L)
        val todo =
            getTodoWithKeyUseCase(GetTodosWithKeyUseCase.Arg(homeHelper.getTodoKey().toString()))
        _uiState.update { it?.copy(id = 1, text = todo?.title.orEmpty()) }
        getAllTodos()
    }

    private suspend fun getAllTodos() {
        val list = getTodosUseCase().map { it.title }
        iterateAllTodos(list)
    }

    private suspend fun iterateAllTodos(list: List<String>) {
        _uiState.update { it?.copy(id = list.count(), text = it.text) }
        list.forEach { todo ->
            delay(100L)
            updateWithData(todo)
        }
        updateWithData(homeHelper.getFinishedStr())
    }

    private fun updateWithData(data: String) = viewModelScope.launch {
        _uiState.update { it?.copy(id = it.id, text = data) }
    }
}