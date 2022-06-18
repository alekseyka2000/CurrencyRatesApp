package com.test.data.api.entity

data class CurrencyRatesDto(
    val base: String,
    val date: String,
    val rates: Rates,
    val success: Boolean,
    val timestamp: Int
)

data class Rates(
    val rates: List<Rate>
)
data class Rate(
    val base: String,
    val rate: Double
)
