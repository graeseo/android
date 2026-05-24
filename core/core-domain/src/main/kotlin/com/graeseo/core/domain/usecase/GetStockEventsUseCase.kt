package com.graeseo.core.domain.usecase

import com.graeseo.core.domain.entity.StockEvent
import com.graeseo.core.domain.repository.StockEventRepository
import kotlinx.coroutines.flow.Flow

/**
 * 주식 이벤트 목록 조회 유스케이스.
 *
 * - ticker가 null이면 전체 이벤트 반환
 * - ticker가 지정되면 해당 종목 이벤트만 반환
 */
class GetStockEventsUseCase(
    private val stockEventRepository: StockEventRepository,
) {
    operator fun invoke(ticker: String? = null): Flow<List<StockEvent>> {
        return if (ticker == null) {
            stockEventRepository.getStockEvents()
        } else {
            stockEventRepository.getStockEventsByTicker(ticker)
        }
    }
}
