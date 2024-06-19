package com.ogrvassiem.myfilms.ui.destinations.gameScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ogrvassiem.myfilms.ui.destinations.gameScreen.cardStack.CardStack
import com.ogrvassiem.myfilms.ui.destinations.gameScreen.cardStack.rememberCardStackController
import com.ogrvassiem.myfilms.ui.theme.Theme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GameScreenUI(viewModel: GameScreenViewModel = hiltViewModel()) {

    val cardStackController = rememberCardStackController()

    val films by viewModel.displayedFilms.collectAsState()

    var cardColor = Theme.colors.white

    films.asReversed().forEach { film ->
        key(film.filmName) {
            val cardIndex = films.indexOf(film)
            val isCurrentCard = cardIndex == 0

            if (isCurrentCard) {
                cardColor = when {
                    cardStackController.offsetX.value < -80f -> Theme.colors.lightRed
                    cardStackController.offsetX.value > 80f -> Theme.colors.lightGreen
                    else -> Theme.colors.bg
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(color = cardColor)
            ) {
                CardStack(
                    modifier = Modifier.fillMaxSize(),
                    onSwipeRight = { viewModel.onSwipeRight() },
                    onSwipeLeft = { viewModel.onSwipeLeft() },
                    viewModel = viewModel,
                    cardStackController = cardStackController,
                )
            }
        }
    }
}