package com.test.domain

import com.test.domain.entity.CurrencyNameModel
import com.test.domain.entity.RateModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Use case provide available currencies for show rates
 *
 *  @author YarakhovichAA
 */

@Singleton
class GetFavoriteCurrencyRatesUseCaseImpl @Inject constructor(
    private val currencyGateway: CurrencyGateway,
) : GetFavoriteCurrencyRatesUseCase {

    private val popularCurrencyList = currencyGateway.getFavoriteCurrencyList()

    override suspend fun getFavoriteCurrencyRates(model: CurrencyNameModel): List<RateModel> {
        return currencyGateway.getCurrencyRates(model)
            .filter { popularCurrencyList.contains(it.base.uppercase()) }
    }
}