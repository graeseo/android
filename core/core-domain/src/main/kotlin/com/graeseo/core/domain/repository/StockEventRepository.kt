package com.graeseo.core.domain.repository

import com.graeseo.core.domain.entity.StockEvent
import kotlinx.coroutines.flow.Flow

/**
 * 주식 이벤트 데이터 소스 인터페이스.
 * core-data 레이어에서 구현됩니다.
 */
interface StockEventRepository {
    /**
     * 전체 주식 이벤트 목록을 스트림으로 반환합니다.
     */
    fun getStockEvents(): Flow<List<StockEvent>>

    /**
     * 특정 티커의 이벤트 목록을 스트림으로 반환합니다.
     *
     * @param ticker 필터링할 주식 티커 (e.g. "TSLA")
     */
    fun getStockEventsByTicker(ticker: String): Flow<List<StockEvent>>
}
