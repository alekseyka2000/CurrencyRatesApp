package com.test.domain

import com.test.domain.entity.CurrencyNameModel
import com.test.domain.entity.RateModel

interface GetFavoriteCurrencyRatesUseCase {

    suspend fun getFavoriteCurrencyRates(model: CurrencyNameModel): List<RateModel>
}