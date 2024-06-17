package com.ogrvassiem.myfilms

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import com.ogrvassiem.myfilms.ui.ApplicationRootUI
import com.ogrvassiem.myfilms.ui.theme.Theme
import com.ogrvassiem.myfilms.utils.LocalActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        enableEdgeToEdge(statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.BLACK))

        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider(LocalActivity provides this@MainActivity) {
                Theme {
                    ApplicationRootUI()
                }
            }
        }
    }
}