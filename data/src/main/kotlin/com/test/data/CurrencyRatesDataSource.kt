package com.test.data

import javax.inject.Singleton

@Singleton
interface CurrencyRatesDataSource {

    suspend fun getAvailableCurrencies(base: String)
    suspend fun getCurrencyRates(base: String)
}