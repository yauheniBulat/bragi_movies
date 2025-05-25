package com.test.bragimovies.data.model

data class GenreResponse(
    val genres: List<GenreDto>
)
data class GenreDto(
    val id: Int,
    val name: String
)