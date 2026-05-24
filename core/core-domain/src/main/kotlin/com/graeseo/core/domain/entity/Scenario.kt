package com.graeseo.core.domain.entity

/**
 * 이벤트에 대한 시나리오 인사이트.
 *
 * 예: "테슬라 실적 발표 → 어닝 서프라이즈 시 주가 상승 확률 70%"
 *
 * @param id 시나리오 고유 ID
 * @param event 연관 이벤트
 * @param title 시나리오 제목
 * @param bullCase 강세 시나리오 설명
 * @param bearCase 약세 시나리오 설명
 * @param probability 강세 시나리오 확률 (0.0 ~ 1.0)
 */
data class Scenario(
    val id: String,
    val event: StockEvent,
    val title: String,
    val bullCase: String,
    val bearCase: String,
    val probability: Double,
) {
    init {
        require(probability in 0.0..1.0) {
            "probability must be between 0.0 and 1.0, but was $probability"
        }
    }
}
