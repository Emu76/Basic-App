package com.peteremo.data.extensions

import retrofit2.Response

object NetworkExtensions {
    fun <T> Response<T>.check(): T? {
        if (this.isSuccessful) {
            return this.body()
        } else {
            throw Exception(this.errorBody()?.string().orEmpty())
        }
    }
}