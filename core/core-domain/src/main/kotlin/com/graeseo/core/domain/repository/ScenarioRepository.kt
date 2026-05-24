package com.graeseo.core.domain.repository

import com.graeseo.core.domain.entity.Scenario
import kotlinx.coroutines.flow.Flow

/**
 * 시나리오 인사이트 데이터 소스 인터페이스.
 * core-data 레이어에서 구현됩니다.
 */
interface ScenarioRepository {
    /**
     * 특정 이벤트 ID에 연관된 시나리오 목록을 스트림으로 반환합니다.
     *
     * @param eventId 이벤트 고유 ID
     */
    fun getScenariosForEvent(eventId: String): Flow<List<Scenario>>
}
