package com.test.domain

import com.test.domain.entity.CurrencyModel
import javax.inject.Singleton

@Singleton
interface GetCurrenciesUseCase {

    suspend fun getCurrenciesList(): List<CurrencyModel>
}