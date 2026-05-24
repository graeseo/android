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
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MainFeedScreen(
    viewModel: MainFeedViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            GraeseoBottomNavigationBar(
                selectedTab = uiState.selectedTab,
                onTabSelected = viewModel::onTabSelected,
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            MainFeedWebView(url = uiState.feedUrl)
        }
    }
}

@Composable
private fun MainFeedWebView(url: String) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
            }
        },
        update = { webView ->
            if (webView.url != url) {
                webView.loadUrl(url)
            }
        },
        modifier = Modifier.fillMaxSize(),
        onRelease = { webView ->
            webView.stopLoading()
            webView.destroy()
        },
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
