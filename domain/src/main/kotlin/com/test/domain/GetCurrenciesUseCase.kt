package com.test.domain

import com.test.domain.entity.CurrencyNameModel

interface GetCurrenciesUseCase {

    suspend fun getCurrenciesList(): List<CurrencyNameModel>
}