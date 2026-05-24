package com.graeseo.core.domain.entity

/**
 * 주식 종목 정보.
 *
 * @param ticker 티커 심볼 (e.g. "TSLA", "AAPL")
 * @param name 종목 한글/영문 이름 (e.g. "테슬라")
 */
data class Stock(
    val ticker: String,
    val name: String,
)
