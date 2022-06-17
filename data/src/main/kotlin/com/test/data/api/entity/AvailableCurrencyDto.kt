package com.test.data.api.entity

data class AvailableCurrencyDto(
    val success: Boolean,
    val symbols: Symbols
)

data class Symbols(
    val names: List<Name>
)
data class Name(
    val base: String,
    val name: String
)