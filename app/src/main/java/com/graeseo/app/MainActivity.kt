package com.graeseo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.graeseo.core.ui.theme.GraeseoTheme
import com.graeseo.feature.mainfeed.MainFeedScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraeseoTheme {
                MainFeedScreen()
            }
        }
    }
}
