package com.test.bragimovies.domain.model

data class MovieInfo(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val rating: Double,
    val budget: Long,
    val revenue: Long
)