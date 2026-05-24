package com.graeseo.core.data.repository

import com.graeseo.core.domain.entity.Scenario
import com.graeseo.core.domain.repository.ScenarioRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * ScenarioRepository 구현체.
 * TODO: 실제 API/DB 연동 시 이 파일을 수정합니다.
 */
class ScenarioRepositoryImpl @Inject constructor() : ScenarioRepository {

    override fun getScenariosForEvent(eventId: String): Flow<List<Scenario>> {
        // TODO: 실제 데이터 소스 연동
        return flowOf(emptyList())
    }
}
