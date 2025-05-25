package com.test.bragimovies.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.bragimovies.domain.model.Genre
import com.test.bragimovies.domain.model.MovieInfo
import com.test.bragimovies.domain.usecase.GetGenresUseCase
import com.test.bragimovies.domain.usecase.GetMoviesUseCase
import com.test.bragimovies.presentation.model.MovieResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getGenresUseCase: GetGenresUseCase
) : ViewModel() {

    private val _genres = MutableStateFlow<MovieResult<List<Genre>>>(MovieResult.Loading)
    val genres: StateFlow<MovieResult<List<Genre>>> = _genres.asStateFlow()

    private val _movies = MutableStateFlow<MovieResult<List<MovieInfo>>>(MovieResult.Success(emptyList()))
    val movies: StateFlow<MovieResult<List<MovieInfo>>> = _movies.asStateFlow()

    private var selectedGenre: Int? = null

    val getSelectedGenre: Int?
        get() = selectedGenre

    fun fetchGenres() {
        viewModelScope.launch {
            _genres.value = MovieResult.Loading
            Log.d("MovieViewModel", "Fetching genres...")
            try {
                val genresResult = getGenresUseCase()
                Log.d("MovieViewModel", "Genres fetched: ${genresResult.size}")
                _genres.value = MovieResult.Success(genresResult)
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Failed to fetch genres", e)
                _genres.value = MovieResult.Error(e)
            }
        }
    }

    fun selectGenre(genreId: Int?) {
        Log.d("MovieViewModel", "selectGenre genreId = $genreId")
        selectedGenre = genreId
    }

    fun getFilteredMovies() {
        viewModelScope.launch {
            Log.d("MovieViewModel", "Fetching movies for genreId = $selectedGenre")
            _movies.value = MovieResult.Loading
            try {
                getMoviesUseCase(selectedGenre).collect { result ->
                    Log.d("MovieViewModel", "Movies fetched: ${result.size}")
                    _movies.value = MovieResult.Success(result)
                }
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Failed to fetch movies", e)
                _movies.value = MovieResult.Error(e)
            }
        }
    }
}
