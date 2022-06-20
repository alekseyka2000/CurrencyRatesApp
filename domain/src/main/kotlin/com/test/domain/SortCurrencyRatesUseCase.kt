package com.test.domain

import com.test.domain.entity.RateModel
import javax.inject.Singleton

@Singleton
interface SortCurrencyRatesUseCase {

    fun sortCurrencyRates(rateList: List<RateModel>): List<RateModel>
}