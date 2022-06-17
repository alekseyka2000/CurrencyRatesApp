package com.test.data.api

import com.test.data.api.entity.AvailableCurrencyDto
import javax.inject.Singleton

@Singleton
interface CurrencyApiDataSource {

    suspend fun getAvailableCurrencies(): AvailableCurrencyDto
    suspend fun getCurrencyRates(base: String)
}