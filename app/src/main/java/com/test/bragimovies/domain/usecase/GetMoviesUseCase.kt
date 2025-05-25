package com.test.bragimovies.domain.usecase

import com.test.bragimovies.domain.model.MovieInfo
import com.test.bragimovies.domain.repositiory.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlin.Int

class GetMoviesUseCase(private val repository: MovieRepository) {
    operator fun invoke(genreId: Int?): Flow<List<MovieInfo>> {
        return repository.getMoviesByGenre(genreId)
            .flatMapConcat { movies ->
                flow {
                    val detailedMovies = movies.map { movie ->
                        val details = repository.getMovieDetails(movie.id)
                        MovieInfo(
                            id = movie.id,
                            title = movie.title,
                            posterPath = movie.posterPath,
                            rating = movie.rating,
                            budget = details.budget,
                            revenue = details.revenue
                        )
                    }
                    emit(detailedMovies)
                }
            }
    }
}