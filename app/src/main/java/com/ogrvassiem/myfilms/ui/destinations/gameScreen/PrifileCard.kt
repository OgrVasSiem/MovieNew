package com.ogrvassiem.myfilms.ui.destinations.gameScreen

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.ogrvassiem.myfilms.ui.destinations.gameScreen.cardStack.CardStackController
import com.ogrvassiem.myfilms.ui.theme.Theme

@Composable
fun ProfileCard(
    modifier: Modifier = Modifier,
    poster: String,
    scaleFactor: Float = 1f,
    title: String,
    descriptions: String,
    genres: String,
    years: String,
    rating: String
) {
    Card(
        modifier = modifier
            .scale(scaleFactor)
            .clip(RoundedCornerShape(16.dp))
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(
            modifier = Modifier
                .scale(scaleFactor)
                .clip(RoundedCornerShape(16.dp))
                .background(color = Theme.colors.bg)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(poster)
                    .build(),
                contentDescription = null,
            )

            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp),
                text = title,
                color = Theme.colors.white,
                style = Theme.textStyles.filmName
            )
        }
    }
}