package com.graeseo.feature.mainfeed

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

/**
 * 메인피드 화면.
 *
 * 구조: WebView(메인피드 콘텐츠) + 네이티브 Compose BottomNavigationBar
 * TODO: WebView URL을 환경별 설정에서 주입받도록 리팩터링
 */
@Composable
fun MainFeedScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            GraeseoBottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            MainFeedWebView()
        }
    }
}

@Composable
private fun MainFeedWebView() {
    // TODO: URL을 BuildConfig 또는 RemoteConfig에서 주입
    val feedUrl = "about:blank"

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(feedUrl)
            }
        },
        modifier = Modifier.fillMaxSize(),
    )
}

@Composable
private fun GraeseoBottomNavigationBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
) {
    val tabs = listOf(
        BottomNavTab(label = "홈", icon = Icons.Filled.Home),
        BottomNavTab(label = "알림", icon = Icons.Filled.Notifications),
        BottomNavTab(label = "마이", icon = Icons.Filled.Person),
    )

    NavigationBar {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                icon = {
                    Icon(imageVector = tab.icon, contentDescription = tab.label)
                },
                label = { Text(tab.label) },
            )
        }
    }
}

private data class BottomNavTab(
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
)
