package com.ogrvassiem.myfilms.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class TextStyles(
    val title: TextStyle,
    val buttonText: TextStyle,
    val filmName: TextStyle
) {
    constructor() : this(
        title = TextStyle(
            fontSize = 17.sp,
            fontWeight = FontWeight(600),
            lineHeight = 22.sp,
            letterSpacing = (-0.41).sp
        ),

        buttonText = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            lineHeight = 16.sp,
        ),
        filmName = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight(800),
            lineHeight = 30.sp,
        ),
    )
}

val LocalTextStyles = staticCompositionLocalOf { TextStyles() }