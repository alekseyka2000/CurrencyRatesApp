package com.test.domain

import com.test.domain.entity.CurrencyNameModel
import javax.inject.Inject

/**
 * Use case provide available currencies for show rates
 *
 *  @author YarakhovichAA
 */

class GetCurrenciesUseCaseImpl @Inject constructor(
    private val currencyGateway: CurrencyGateway
) : GetCurrenciesUseCase {

    private val necessaryCurrencyBaseList = listOf("USD", "EUR", "RUB", "BYN")

    override suspend fun getCurrenciesList(): List<CurrencyNameModel> {
        return currencyGateway.getAvailableCurrencies()
            .filter { necessaryCurrencyBaseList.contains(it.base.uppercase()) }
    }
}