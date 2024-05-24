package com.example.macropay.ui.movies

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.macropay.R
import com.example.macropay.core.extensions.empty
import com.example.macropay.domain.model.Movie
import com.example.macropay.ui.theme.MicroPayChallengeTheme
import com.example.macropay.ui.theme.Space12
import com.example.macropay.ui.theme.Space16
import com.example.macropay.ui.theme.Space4
import com.example.macropay.ui.theme.Space8


@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun MovieItem(modifier: Modifier = Modifier, movie: Movie, onMovementClick: (id: Int) -> Unit) {
    Card(
        modifier = modifier.fillMaxSize(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Space4
        ),
        onClick = { onMovementClick(movie.id) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                model = movie.image,
                contentDescription = String.empty()
            )
            Spacer(modifier = Modifier.height(Space8))
            Text(
                modifier = Modifier
                    .padding(horizontal = Space8),
                textAlign = TextAlign.Center,
                text = movie.title,
                style = MaterialTheme.typography.labelLarge

            )
            Spacer(modifier = Modifier.height(Space4))
            Text(
                modifier = Modifier
                    .padding(horizontal = Space8),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.rating, movie.rating),
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.height(Space12))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LabelMovementPreview() {
    MicroPayChallengeTheme {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(vertical = Space16, horizontal = Space8),
            horizontalArrangement = Arrangement.spacedBy(Space8),
            verticalItemSpacing = Space8
            ) {
            items(
                items = givenMovies(),
                key = { it.id }
            ) {
                MovieItem(
                    movie = it,
                    onMovementClick = {}
                )
            }
        }
    }
}
