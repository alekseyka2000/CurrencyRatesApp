package com.test.domain

import com.test.domain.entity.CurrencyModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface CurrencyGateway {

    suspend fun getAvailableCurrencies(): List<CurrencyModel>
    suspend fun getCurrencyRates(base: String)
}