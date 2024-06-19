package com.ogrvassiem.myfilms.roote

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ogrvassiem.myfilms.ui.destinations.gameScreen.GameScreenUI
import com.ogrvassiem.myfilms.ui.destinations.navArgs.CategoriesNavArgs
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@Destination(navArgsDelegate = CategoriesNavArgs::class)
@RootNavGraph
@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    GameScreenUI()
}