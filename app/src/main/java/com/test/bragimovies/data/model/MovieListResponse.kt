package com.test.bragimovies.data.model

data class MovieListResponse(
    val results: List<MovieDto>
)

data class MovieDto(
    val id: Int,
    val title: String,
    val poster_path: String?,
    val vote_average: Double
)