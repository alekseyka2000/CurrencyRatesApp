package com.test.data.api

import com.test.data.api.entity.AvailableCurrencyDto
import com.test.data.api.entity.CurrencyRatesDto

/**
 * Network data source for getting currency rates data
 */

interface CurrencyApiDataSource {

    suspend fun getAvailableCurrencies(): AvailableCurrencyDto
    suspend fun getCurrencyRates(base: String): CurrencyRatesDto
}