package com.ogrvassiem.myfilms.ui.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object Theme {

    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current


    val textStyles: TextStyles
        @Composable
        @ReadOnlyComposable
        get() = LocalTextStyles.current
}

@Composable
fun Theme(content: @Composable () -> Unit) {
    val rippleIndication = rememberRipple()

    CompositionLocalProvider(
        LocalIndication provides rippleIndication,
    ) {
        content()
    }
}