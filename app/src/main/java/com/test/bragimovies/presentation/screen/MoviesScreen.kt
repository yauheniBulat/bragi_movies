package com.test.bragimovies.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.test.bragimovies.R
import com.test.bragimovies.domain.model.MovieInfo
import com.test.bragimovies.presentation.component.MovieCard
import com.test.bragimovies.presentation.model.MovieResult
import com.test.bragimovies.presentation.viewmodel.MovieViewModel

@Composable
fun MoviesScreen(
    viewModel: MovieViewModel = hiltViewModel(),
    onFilterClick: () -> Unit
) {
    val state = viewModel.movies.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getFilteredMovies()
    }
    Scaffold(
        floatingActionButton = {
            androidx.compose.material3.FloatingActionButton(onClick = onFilterClick) {
                androidx.compose.material3.Icon(Icons.Default.FilterList, contentDescription = "Filter")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (state.value) {
                is MovieResult.Loading -> Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
                is MovieResult.Error -> Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(stringResource(R.string.connection_error))
                }
                is MovieResult.Success<*> -> {
                    val movies = (state.value as MovieResult.Success<List<MovieInfo>>).data
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(movies) { movie ->
                            MovieCard(movie)
                        }
                    }
                }
            }
        }
    }
}