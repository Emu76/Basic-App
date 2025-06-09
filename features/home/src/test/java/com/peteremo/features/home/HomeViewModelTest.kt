package com.peteremo.features.home

import com.peteremo.basicapp.features.home.HomeViewModel
import com.peteremo.basicapp.features.home.helper.HomeHelper
import com.peteremo.domain.home.model.ToDoDomainModel
import com.peteremo.domain.home.usecase.GetTodosUseCase
import com.peteremo.domain.home.usecase.GetTodosWithKeyUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

import org.junit.Before

private const val MOCK_ID = 1
private const val MOCK_TITLE_1 = "Title_1"
private const val MOCK_TEST = "Test"
private const val MOCK_TITLE_2 = "Title_2"

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    private val mockTodosUseCase = mockk<GetTodosUseCase>(relaxed = true)
    private val mockTodoKeyUseCase = mockk<GetTodosWithKeyUseCase>(relaxed = true)
    private val mockHomeHelper = mockk<HomeHelper>(relaxed = true)

    private val mockTodoModel = ToDoDomainModel(MOCK_ID, MOCK_TITLE_1)
    private val mockTodoListModel = ToDoDomainModel(MOCK_ID, MOCK_TITLE_2)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        coEvery { mockTodosUseCase() } returns listOf(mockTodoListModel)
        coEvery { mockTodoKeyUseCase(any()) } returns mockTodoModel
        coEvery { mockHomeHelper.getStartTitle() } returns MOCK_TEST

        viewModel = HomeViewModel(mockTodosUseCase, mockTodoKeyUseCase, mockHomeHelper)
    }

    @Test
    fun `WHEN screen opens THEN display test text`() = runTest {
        Assert.assertEquals(MOCK_TEST, viewModel.uiState.value?.text)
    }

    @Test
    fun `WHEN screen waits for over 1 second THEN display first mock`() = runBlocking {
        coVerify { mockHomeHelper.getStartTitle() }
        delay(1050L)
        Assert.assertEquals(MOCK_TITLE_1, viewModel.uiState.value?.text)
    }

    @Test
    fun `WHEN screen waits for over 2 s THEN display list mock`() = runBlocking {
        coVerify { mockHomeHelper.getStartTitle() }
        delay(2000L)
        Assert.assertEquals(MOCK_TITLE_2, viewModel.uiState.value?.text)
    }
}