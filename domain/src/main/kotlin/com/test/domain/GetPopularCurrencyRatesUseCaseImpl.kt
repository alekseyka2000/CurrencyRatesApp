package com.test.domain

import android.util.Log
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
class GetPopularCurrencyRatesUseCaseImpl @Inject constructor(
    private val currencyGateway: CurrencyGateway
) : GetPopularCurrencyRatesUseCase {

    private val popularCurrencyList = listOf("USD", "EUR", "RUB", "BYN")

    override suspend fun getPopularCurrencyRates(model: CurrencyNameModel): List<RateModel> {
        Log.d("Test1", model.base)
        return currencyGateway.getCurrencyRates(model)
            .filter { popularCurrencyList.contains(it.base.uppercase()) }
    }
}