package com.graeseo.core.domain.usecase

import com.graeseo.core.domain.entity.Scenario
import com.graeseo.core.domain.entity.Stock
import com.graeseo.core.domain.entity.StockEvent
import com.graeseo.core.domain.entity.StockEventType
import com.graeseo.core.domain.repository.ScenarioRepository
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

@DisplayName("GetScenariosForEventUseCase")
class GetScenariosForEventUseCaseTest {

    private lateinit var fakeRepository: FakeScenarioRepository
    private lateinit var useCase: GetScenariosForEventUseCase

    private val tslaEarningsEvent = StockEvent(
        id = "event-1",
        stock = Stock(ticker = "TSLA", name = "테슬라"),
        type = StockEventType.EARNINGS,
        eventDate = LocalDate.of(2025, 4, 23),
        title = "테슬라 1Q25 실적 발표",
        description = "2025년 1분기 실적 발표",
    )

    @BeforeEach
    fun setUp() {
        fakeRepository = FakeScenarioRepository()
        useCase = GetScenariosForEventUseCase(fakeRepository)
    }

    @Nested
    @DisplayName("이벤트 ID로 시나리오를 조회할 때")
    inner class WhenFetchingScenariosForEvent {

        @Test
        @DisplayName("해당 이벤트에 연결된 시나리오 목록을 반환한다")
        fun returnsScenariosLinkedToEvent() = runTest {
            // given
            fakeRepository.scenariosByEventId = mapOf(
                "event-1" to listOf(
                    buildScenario(id = "s1", event = tslaEarningsEvent, probability = 0.65),
                    buildScenario(id = "s2", event = tslaEarningsEvent, probability = 0.35),
                )
            )

            // when
            val result = useCase("event-1").first()

            // then
            assertEquals(2, result.size)
            assertTrue(result.all { it.event.id == "event-1" })
        }

        @Test
        @DisplayName("시나리오가 없는 이벤트 ID 조회 시 빈 목록을 반환한다")
        fun returnsEmptyListWhenNoScenariosForEvent() = runTest {
            // given
            fakeRepository.scenariosByEventId = emptyMap()

            // when
            val result = useCase("event-99").first()

            // then
            assertTrue(result.isEmpty())
        }

        @Test
        @DisplayName("다른 이벤트의 시나리오는 포함되지 않는다")
        fun doesNotIncludeScenariosFromOtherEvents() = runTest {
            // given
            val aaplEvent = tslaEarningsEvent.copy(
                id = "event-2",
                stock = Stock(ticker = "AAPL", name = "애플"),
            )
            fakeRepository.scenariosByEventId = mapOf(
                "event-1" to listOf(buildScenario(id = "s1", event = tslaEarningsEvent)),
                "event-2" to listOf(buildScenario(id = "s2", event = aaplEvent)),
            )

            // when
            val result = useCase("event-1").first()

            // then
            assertEquals(1, result.size)
            assertEquals("s1", result[0].id)
        }
    }

    @Nested
    @DisplayName("시나리오 확률 유효성 검사")
    inner class ScenarioProbabilityValidation {

        @Test
        @DisplayName("확률이 0.0~1.0 범위면 정상 생성된다")
        fun scenarioCreatedWithValidProbability() {
            // given / when / then
            val scenario = buildScenario(id = "s1", event = tslaEarningsEvent, probability = 0.7)
            assertEquals(0.7, scenario.probability)
        }

        @Test
        @DisplayName("확률이 범위를 벗어나면 IllegalArgumentException이 발생한다")
        fun throwsWhenProbabilityOutOfRange() {
            // given / when / then
            val exception = org.junit.jupiter.api.Assertions.assertThrows(
                IllegalArgumentException::class.java
            ) {
                buildScenario(id = "s1", event = tslaEarningsEvent, probability = 1.5)
            }
            assertTrue(exception.message!!.contains("probability"))
        }
    }

    // ─── Test Helper ───────────────────────────────────────────────────────────

    private fun buildScenario(
        id: String,
        event: StockEvent,
        probability: Double = 0.5,
    ) = Scenario(
        id = id,
        event = event,
        title = "시나리오 $id",
        bullCase = "강세: 주가 상승 예상",
        bearCase = "약세: 주가 하락 가능",
        probability = probability,
    )

    // ─── Fake Repository ───────────────────────────────────────────────────────

    private class FakeScenarioRepository : ScenarioRepository {
        var scenariosByEventId: Map<String, List<Scenario>> = emptyMap()

        override fun getScenariosForEvent(eventId: String): Flow<List<Scenario>> =
            flowOf(scenariosByEventId[eventId] ?: emptyList())
    }
}
