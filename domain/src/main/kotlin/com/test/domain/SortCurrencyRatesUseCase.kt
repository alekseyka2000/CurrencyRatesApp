package com.test.domain

import com.test.domain.entity.RateModel

interface SortCurrencyRatesUseCase {

    /** sorting [rateList] by chosen filters*/
    fun sortCurrencyRates(rateList: List<RateModel>): List<RateModel>
}