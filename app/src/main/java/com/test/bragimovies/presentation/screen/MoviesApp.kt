package com.test.bragimovies.presentation.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.bragimovies.presentation.viewmodel.MovieViewModel

@Composable
fun MoviesApp() {
    val navController = rememberNavController()
    val viewModel: MovieViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "movies") {
        composable("movies") {
            MoviesScreen(viewModel, onFilterClick = { navController.navigate("filters") })
        }
        composable("filters") {
            FilterScreen(viewModel, onGenreSelected = {
                navController.popBackStack()
            })
        }
    }
}
