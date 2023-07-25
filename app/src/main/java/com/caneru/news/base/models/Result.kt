package com.caneru.news.base.models

sealed class Result<T> {
    data class Loading<T>(val isLoading: Boolean) : Result<T>()
    data class Success<T>(val data: T) : Result<T>()
    data class Failure<T>(val errorMessage: String) : Result<T>()
}