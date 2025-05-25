package com.test.bragimovies.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.test.bragimovies.R
import com.test.bragimovies.presentation.model.MovieResult
import com.test.bragimovies.presentation.viewmodel.MovieViewModel

@Composable
fun FilterScreen(
    viewModel: MovieViewModel = hiltViewModel(),
    onGenreSelected: () -> Unit
) {
    val TAG = "FilterScreen"

    val genres = viewModel.genres.collectAsState()
    val selectedGenre = viewModel.getSelectedGenre

    LaunchedEffect(Unit) {
        Log.d(TAG, "LaunchedEffect: fetching genres...")
        viewModel.fetchGenres()
    }
    Scaffold() { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (val state = genres.value) {
                is MovieResult.Loading -> {
                    Log.d(TAG, "Genres are loading...")
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is MovieResult.Error -> {
                    Log.e(TAG, "Error loading genres")
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(stringResource(R.string.genres_error))
                    }
                }

                is MovieResult.Success -> {
                    val genreList = state.data
                    Log.d(TAG, "Genres loaded successfully: ${genreList.size} items")
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        item {
                            Button(
                                modifier = Modifier.fillMaxSize(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedGenre == null) Color.Blue else Color.LightGray
                                ),
                                onClick = {
                                    Log.d(TAG, "Selected: All")
                                    viewModel.selectGenre(null)
                                    onGenreSelected()
                                }) {
                                Text(stringResource(R.string.genre_all))
                            }
                        }
                        items(genreList) { genre ->
                            Button(
                                modifier = Modifier.fillMaxSize(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedGenre == genre.id) Color.Blue else Color.LightGray
                                ),
                                onClick = {
                                    Log.d(TAG, "Selected genre: ${genre.name} (${genre.id})")
                                    viewModel.selectGenre(genre.id)
                                    onGenreSelected()
                                }) {
                                Text(genre.name)
                            }
                        }
                    }
                }
            }
        }
    }
}
