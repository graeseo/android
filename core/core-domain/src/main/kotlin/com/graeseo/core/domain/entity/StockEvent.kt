package com.graeseo.core.domain.entity

import java.time.LocalDate

/**
 * 주식 이벤트 (실적 발표, 배당 등).
 *
 * @param id 이벤트 고유 ID
 * @param stock 이벤트가 연관된 종목
 * @param type 이벤트 유형
 * @param eventDate 이벤트 예정일
 * @param title 이벤트 제목
 * @param description 이벤트 상세 설명
 */
data class StockEvent(
    val id: String,
    val stock: Stock,
    val type: StockEventType,
    val eventDate: LocalDate,
    val title: String,
    val description: String,
)

enum class StockEventType {
    EARNINGS,       // 실적 발표
    DIVIDEND,       // 배당
    SPLIT,          // 주식 분할
    IPO,            // 신규 상장
    ANALYST_DAY,    // 애널리스트 데이
    OTHER,
}
