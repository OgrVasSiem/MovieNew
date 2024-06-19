package com.ogrvassiem.myfilms.ui.destinations.gameScreen.cardStack

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.ThresholdConfig
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ogrvassiem.myfilms.ui.destinations.gameScreen.GameScreenViewModel
import com.ogrvassiem.myfilms.ui.destinations.gameScreen.ProfileCard

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardStack(
    modifier: Modifier = Modifier,
    thresholdConfig: (Float, Float) -> ThresholdConfig = { _, _ -> FractionalThreshold(0.2f) },
    velocityThreshold: Dp = 125.dp,
    onSwipeLeft: () -> Unit,
    onSwipeRight: () -> Unit,
    viewModel: GameScreenViewModel,
    cardStackController: CardStackController,
) {
    val films by viewModel.displayedFilms.collectAsState()

    val offsets by viewModel.offsets.collectAsState()

    cardStackController.onSwipeLeft = {
        onSwipeLeft()
    }

    cardStackController.onSwipeRight = {
        onSwipeRight()
    }

    Box(
        modifier = modifier
            .draggableStack(
                controller = cardStackController,
                thresholdConfig = thresholdConfig,
                velocityThreshold = velocityThreshold,
            )
    ) {
        films.asReversed().forEach { film ->
            key(film.filmName) {
                val cardIndex = films.indexOf(film)

                val offsetY by animateDpAsState(
                    targetValue = offsets[cardIndex].value,
                    label = "offsetYAnimation"
                )

                val scaleFactor by animateFloatAsState(
                    targetValue = when (cardIndex) {
                        0 -> 1f
                        1 -> 0.85f
                        2 -> 0.65f
                        3 -> 0.45f
                        else -> 1f
                    },
                    label = "scaleAnimation"
                )

                AnimatedVisibility(
                    modifier = Modifier.align(Alignment.Center),
                    visible = films.contains(film),
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut()
                ) {
                    ProfileCard(
                        modifier = Modifier
                            .offset(y = offsetY)
                            .scale(scaleFactor)
                            .moveTo(
                                x = if (cardIndex == 0) cardStackController.offsetX.value else 0f,
                                y = if (cardIndex == 0) cardStackController.offsetY.value else 0f
                            ),
                        poster = film.poster,
                        title = film.filmName,
                        descriptions = film.filmDescription,
                        genres = film.genres,
                        rating = film.rating.toString(),
                        years = film.years,
                    )
                }
            }
        }
    }
}


