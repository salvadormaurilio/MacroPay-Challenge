package com.example.macropay.ui.movie.detail

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.macropay.R
import com.example.macropay.ui.theme.MicroPayChallengeTheme
import com.example.macropay.ui.theme.Space12
import com.example.macropay.ui.theme.Space16
import com.example.macropay.ui.theme.Space4

@Composable
fun MovieDetailLabel(modifier: Modifier = Modifier, @StringRes title: Int, text: String) {
    Column(modifier = modifier.padding(horizontal = Space16)) {
        HorizontalDivider()
        Spacer(modifier = Modifier.height(Space12))
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.titleMedium

        )
        Spacer(modifier = Modifier.height(Space4))
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(modifier = Modifier.height(Space12))
    }
}

@Preview(showBackground = true)
@Composable
fun LabelMovementDetailPreview() {
    MicroPayChallengeTheme {
        MovieDetailLabel(
            title = R.string.release_date,
            text = ANY_MOVIE_RELEASE_DATE
        )
    }
}