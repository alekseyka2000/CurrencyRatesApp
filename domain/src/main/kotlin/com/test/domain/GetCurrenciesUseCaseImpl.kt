package com.test.domain

import com.test.domain.entity.CurrencyModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCurrenciesUseCaseImpl @Inject constructor(
    private val currencyGateway: CurrencyGateway
): GetCurrenciesUseCase {

    private val necessaryCurrencyBaseList = listOf("USD", "EUR", "RUB", "BYN")

    override suspend fun getCurrenciesList(): List<CurrencyModel>{
        return currencyGateway.getAvailableCurrencies()
            .sortedBy { necessaryCurrencyBaseList.contains(it.base) }
    }
}