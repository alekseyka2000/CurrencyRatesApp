package com.test.domain

import com.test.domain.entity.CurrencyNameModel
import javax.inject.Singleton

@Singleton
interface GetCurrenciesUseCase {

    suspend fun getCurrenciesList(): List<CurrencyNameModel>
}