package com.test.bragimovies.presentation.model

sealed class MovieResult<out T> {
    data class Success<out T>(val data: T) : MovieResult<T>()
    data class Error(val exception: Exception) : MovieResult<Nothing>()
    object Loading : MovieResult<Nothing>()
}