package com.test.bragimovies.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val rating: Double,
)
