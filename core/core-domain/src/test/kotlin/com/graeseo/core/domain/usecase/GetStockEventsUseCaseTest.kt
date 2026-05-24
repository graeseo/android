package com.graeseo.core.domain.usecase

import com.graeseo.core.domain.entity.Stock
import com.graeseo.core.domain.entity.StockEvent
import com.graeseo.core.domain.entity.StockEventType
import com.graeseo.core.domain.repository.StockEventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDate

@DisplayName("GetStockEventsUseCase")
class GetStockEventsUseCaseTest {

    private lateinit var fakeRepository: FakeStockEventRepository
    private lateinit var useCase: GetStockEventsUseCase

    @BeforeEach
    fun setUp() {
        fakeRepository = FakeStockEventRepository()
        useCase = GetStockEventsUseCase(fakeRepository)
    }

    @Nested
    @DisplayName("ticker 필터 없이 호출할 때")
    inner class WhenNoTickerFilter {

        @Test
        @DisplayName("전체 이벤트를 모두 반환한다")
        fun returnsAllEvents() = runTest {
            // given
            val tsla = Stock(ticker = "TSLA", name = "테슬라")
            val aapl = Stock(ticker = "AAPL", name = "애플")
            fakeRepository.events = listOf(
                buildEvent(id = "1", stock = tsla),
                buildEvent(id = "2", stock = aapl),
                buildEvent(id = "3", stock = tsla),
            )

            // when
            val result = useCase(ticker = null).first()

            // then
            assertEquals(3, result.size)
        }

        @Test
        @DisplayName("이벤트가 없으면 빈 목록을 반환한다")
        fun returnsEmptyListWhenNoEvents() = runTest {
            // given
            fakeRepository.events = emptyList()

            // when
            val result = useCase(ticker = null).first()

            // then
            assertTrue(result.isEmpty())
        }
    }

    @Nested
    @DisplayName("ticker 필터를 지정할 때")
    inner class WhenTickerFilterProvided {

        @Test
        @DisplayName("TSLA 필터 시 테슬라 이벤트만 반환한다")
        fun returnsOnlyTeslaEventsWhenTslaFilterApplied() = runTest {
            // given
            val tsla = Stock(ticker = "TSLA", name = "테슬라")
            val aapl = Stock(ticker = "AAPL", name = "애플")
            fakeRepository.events = listOf(
                buildEvent(id = "1", stock = tsla),
                buildEvent(id = "2", stock = aapl),
                buildEvent(id = "3", stock = tsla),
            )

            // when
            val result = useCase(ticker = "TSLA").first()

            // then
            assertEquals(2, result.size)
            assertTrue(result.all { it.stock.ticker == "TSLA" })
        }

        @Test
        @DisplayName("존재하지 않는 ticker 필터 시 빈 목록을 반환한다")
        fun returnsEmptyListForUnknownTicker() = runTest {
            // given
            val tsla = Stock(ticker = "TSLA", name = "테슬라")
            fakeRepository.events = listOf(
                buildEvent(id = "1", stock = tsla),
            )

            // when
            val result = useCase(ticker = "NVDA").first()

            // then
            assertTrue(result.isEmpty())
        }

        @Test
        @DisplayName("단일 ticker에 이벤트가 1건만 있어도 정상 반환한다")
        fun returnsSingleEventForTicker() = runTest {
            // given
            val aapl = Stock(ticker = "AAPL", name = "애플")
            fakeRepository.events = listOf(
                buildEvent(id = "1", stock = aapl),
            )

            // when
            val result = useCase(ticker = "AAPL").first()

            // then
            assertEquals(1, result.size)
            assertEquals("AAPL", result[0].stock.ticker)
        }
    }

    // ─── Test Helper ───────────────────────────────────────────────────────────

    private fun buildEvent(
        id: String,
        stock: Stock,
        type: StockEventType = StockEventType.EARNINGS,
    ) = StockEvent(
        id = id,
        stock = stock,
        type = type,
        eventDate = LocalDate.of(2025, 1, 1),
        title = "Test Event $id",
        description = "Test description",
    )

    // ─── Fake Repository ───────────────────────────────────────────────────────

    private class FakeStockEventRepository : StockEventRepository {
        var events: List<StockEvent> = emptyList()

        override fun getStockEvents(): Flow<List<StockEvent>> =
            flowOf(events)

        override fun getStockEventsByTicker(ticker: String): Flow<List<StockEvent>> =
            flowOf(events.filter { it.stock.ticker == ticker })
    }
}
