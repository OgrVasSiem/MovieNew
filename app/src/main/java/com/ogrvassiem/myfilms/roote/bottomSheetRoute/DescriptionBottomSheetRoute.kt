package com.ogrvassiem.myfilms.roote.bottomSheetRoute

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ogrvassiem.myfilms.ui.destinations.gameScreen.DescriptionBottomSheetUI
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.spec.DestinationStyleBottomSheet

@OptIn(ExperimentalMaterialApi::class)
@RootNavGraph
@Destination(style = DestinationStyleBottomSheet::class)
@Composable
fun DescriptionBottomSheetRoute() {
    DescriptionBottomSheetUI()
}