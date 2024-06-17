package com.ogrvassiem.myfilms.roote

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ogrvassiem.myfilms.ui.destinations.mainScreen.MainScreenUI
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@Destination
@RootNavGraph(start = true)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    MainScreenUI()
}