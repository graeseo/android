package com.graeseo.feature.mainfeed

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainFeedViewModelTest {

    private val testUrl = "https://test.graeseo.com"

    private fun createViewModel(url: String = testUrl) = MainFeedViewModel(feedUrl = url)

    @Test
    fun `초기 feedUrl은 주입된 URL과 동일하다`() {
        val vm = createViewModel()
        assertEquals(testUrl, vm.uiState.value.feedUrl)
    }

    @Test
    fun `초기 선택 탭은 0번(홈)이다`() {
        val vm = createViewModel()
        assertEquals(0, vm.uiState.value.selectedTab)
    }

    @Test
    fun `onTabSelected 호출 시 selectedTab이 변경된다`() {
        val vm = createViewModel()
        vm.onTabSelected(1)
        assertEquals(1, vm.uiState.value.selectedTab)
    }

    @Test
    fun `탭을 두 번 변경하면 마지막 값이 유지된다`() {
        val vm = createViewModel()
        vm.onTabSelected(1)
        vm.onTabSelected(2)
        assertEquals(2, vm.uiState.value.selectedTab)
    }

    @Test
    fun `같은 탭을 다시 선택해도 상태가 유지된다`() {
        val vm = createViewModel()
        vm.onTabSelected(0)
        assertEquals(0, vm.uiState.value.selectedTab)
    }

    @Test
    fun `빈 URL도 그대로 주입된다`() {
        val vm = createViewModel(url = "")
        assertEquals("", vm.uiState.value.feedUrl)
    }
}
