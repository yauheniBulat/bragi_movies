package com.test.bragimovies.domain.usecase

import com.test.bragimovies.domain.model.Genre
import com.test.bragimovies.domain.repositiory.MovieRepository

class GetGenresUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(): List<Genre> {
        return repository.getGenres()
    }
}