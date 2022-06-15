package com.test.data.api

import javax.inject.Singleton

@Singleton
interface CurrencyApiDataSource {

    suspend fun getAvailableCurrencies()
    suspend fun getCurrencyRates(base: String)
}