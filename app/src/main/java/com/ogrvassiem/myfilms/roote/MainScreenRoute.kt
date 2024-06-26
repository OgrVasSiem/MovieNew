package com.ogrvassiem.myfilms.roote

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ogrvassiem.myfilms.destinations.WaitingRoomScreenDestination
import com.ogrvassiem.myfilms.navGraphs.RootNavigator
import com.ogrvassiem.myfilms.ui.destinations.navArgs.CategoriesNavArgs
import com.ogrvassiem.myfilms.ui.destinations.mainScreen.MainScreenUI
import com.ogrvassiem.myfilms.ui.destinations.mainScreen.MainScreenViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@Destination
@RootNavGraph(start = true)
@Composable
fun MainScreen(
    rootNavigator: RootNavigator,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val selectedCardsNames = viewModel.selectedCategories
    MainScreenUI(
        navigateToWaitingRoom = {
            rootNavigator.navigate(
                WaitingRoomScreenDestination(
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