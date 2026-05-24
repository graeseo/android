package com.graeseo.core.domain.usecase

import com.graeseo.core.domain.entity.Scenario
import com.graeseo.core.domain.repository.ScenarioRepository
import kotlinx.coroutines.flow.Flow

/**
 * 특정 이벤트에 대한 시나리오 인사이트 조회 유스케이스.
 */
class GetScenariosForEventUseCase(
    private val scenarioRepository: ScenarioRepository,
) {
    operator fun invoke(eventId: String): Flow<List<Scenario>> {
        return scenarioRepository.getScenariosForEvent(eventId)
    }
}
