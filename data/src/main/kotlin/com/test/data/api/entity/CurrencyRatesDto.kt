package com.test.data.api.entity

data class CurrencyRatesDto(
    val base: String,
    val date: String,
    val rates: String,
    val success: Boolean,
    val timestamp: Int
)
