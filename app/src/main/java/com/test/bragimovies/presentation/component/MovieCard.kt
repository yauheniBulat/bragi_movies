package com.test.bragimovies.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.test.bragimovies.domain.model.MovieInfo

@Composable
fun MovieCard(movie: MovieInfo) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column {
            AsyncImage(
                model = movie.posterPath,
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Text(text = movie.title, fontWeight = FontWeight.Bold)
            Text(text = "Rating: ${movie.rating}")
            Text(text = "Revenue: ${movie.revenue}$")
            Text(text = "Budget: ${movie.budget}$")
        }
    }
}
