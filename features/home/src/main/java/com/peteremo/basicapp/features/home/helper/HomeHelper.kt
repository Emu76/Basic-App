package com.peteremo.basicapp.features.home.helper

import android.content.Context
import com.peteremo.basicapp.features.home.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val TODO_KEY = 1
private const val START_TITLE = "Test"

class HomeHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getTodoKey() = TODO_KEY

    fun getStartTitle() = START_TITLE

    fun getFinishedStr() = context.getString(R.string.finished)
}