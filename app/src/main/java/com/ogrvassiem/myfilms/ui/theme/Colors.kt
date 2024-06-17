package com.ogrvassiem.myfilms.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Stable
class Colors(
    white: Color,
    black: Color,
    divider: Color,
    textGray: Color,
) {
    var white: Color by mutableStateOf(white)
        private set

    var black: Color by mutableStateOf(black)
        private set

    var divider: Color by mutableStateOf(divider)
        private set

    var textGray: Color by mutableStateOf(textGray)
        private set


    override fun toString(): String {
        return """Colors(
            white=$white,
            black=$black,
            divider=$divider,
            textGray=$textGray,
        )"""
    }
}

fun lightColors(): Colors = Colors(
    white = Color(0xffffffff),
    black = Color(0xff000000),
    divider = Color(0xFF343D4E),
    textGray = Color(0xFFE2E4F8),
)

val LocalColors = staticCompositionLocalOf { lightColors() }