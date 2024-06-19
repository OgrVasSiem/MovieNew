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
    bg: Color,
    gold: Color,
    lightGreen: Color,
    lightRed: Color,
) {
    var white: Color by mutableStateOf(white)
        private set

    var black: Color by mutableStateOf(black)
        private set

    var divider: Color by mutableStateOf(divider)
        private set

    var textGray: Color by mutableStateOf(textGray)
        private set

    var bg: Color by mutableStateOf(bg)
        private set

    var gold: Color by mutableStateOf(gold)
        private set

    var lightGreen: Color by mutableStateOf(lightGreen)
        private set

    var lightRed: Color by mutableStateOf(lightRed)
        private set


    override fun toString(): String {
        return """Colors(
            white=$white,
            black=$black,
            divider=$divider,
            textGray=$textGray,
            bg=$bg,
            gold=$gold,
            lightGreen=$lightGreen,
            lightRed=$lightRed,
        )"""
    }
}

fun lightColors(): Colors = Colors(
    white = Color(0xffffffff),
    black = Color(0xff000000),
    divider = Color(0xFF343D4E),
    textGray = Color(0xFFE2E4F8),
    bg = Color(0xFFF6E6E9),
    gold = Color(0xFFE5B769),
    lightGreen = Color(0xFFDAFFDB),
    lightRed = Color(0xFFFFDADA)
)

val LocalColors = staticCompositionLocalOf { lightColors() }