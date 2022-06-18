package com.test.domain

import com.test.domain.entity.CurrencyModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Use case provide available currencies for show rates
 *
 *  @author YarakhovichAA
 */

@Singleton
class GetCurrenciesUseCaseImpl @Inject constructor(
    private val currencyGateway: CurrencyGateway
) : GetCurrenciesUseCase {

    private val necessaryCurrencyBaseList = listOf("USD", "EUR", "RUB", "BYN")

    override suspend fun getCurrenciesList(): List<CurrencyModel> {
        return currencyGateway.getAvailableCurrencies()
            .filter { necessaryCurrencyBaseList.contains(it.base.uppercase()) }
    }
}