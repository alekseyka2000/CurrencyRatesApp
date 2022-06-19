package com.test.domain

import com.test.domain.entity.CurrencyNameModel
import com.test.domain.entity.RateModel
import javax.inject.Singleton

@Singleton
interface GetPopularCurrencyRatesUseCase {

    suspend fun getPopularCurrencyRates(model: CurrencyNameModel): List<RateModel>
}