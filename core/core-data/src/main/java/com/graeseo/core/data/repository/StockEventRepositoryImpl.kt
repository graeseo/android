package com.graeseo.core.data.repository

import com.graeseo.core.domain.entity.StockEvent
import com.graeseo.core.domain.repository.StockEventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * StockEventRepository 구현체.
 * TODO: 실제 API/DB 연동 시 이 파일을 수정합니다.
 */
class StockEventRepositoryImpl @Inject constructor() : StockEventRepository {

    override fun getStockEvents(): Flow<List<StockEvent>> {
        // TODO: 실제 데이터 소스 연동
        return flowOf(emptyList())
    }

    override fun getStockEventsByTicker(ticker: String): Flow<List<StockEvent>> {
        // TODO: 실제 데이터 소스 연동
        return flowOf(emptyList())
    }
}
