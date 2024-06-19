package com.ogrvassiem.myfilms.roote

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ogrvassiem.myfilms.destinations.GameScreenDestination
import com.ogrvassiem.myfilms.navGraphs.RootNavigator
import com.ogrvassiem.myfilms.ui.destinations.navArgs.CategoriesNavArgs
import com.ogrvassiem.myfilms.ui.destinations.waitingRoomScreen.WaitingRoomScreenUI
import com.ogrvassiem.myfilms.ui.destinations.waitingRoomScreen.WaitingRoomScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@Destination(navArgsDelegate = CategoriesNavArgs::class)
@RootNavGraph
@Composable
fun WaitingRoomScreen(
    viewModel: WaitingRoomScreenViewModel = hiltViewModel(),
    rootNavigator: RootNavigator
) {
    val selectedCardsNames = viewModel.categories

    WaitingRoomScreenUI(
        navigateToGameScreen = {
            rootNavigator.navigate(
                GameScreenDestination(
                    navArgs = CategoriesNavArgs(
                        categories = ArrayList(
                            selectedCardsNames
                        )
                    )
                )
            )
        },
        viewModel = viewModel
    )

}