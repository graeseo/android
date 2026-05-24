package com.graeseo.feature.mainfeed

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainFeedViewModel @Inject constructor(
    @Named("feedUrl") feedUrl: String,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainFeedUiState(feedUrl = feedUrl))
    val uiState: StateFlow<MainFeedUiState> = _uiState.asStateFlow()

    fun onTabSelected(index: Int) {
        _uiState.update { it.copy(selectedTab = index) }
    }
}
