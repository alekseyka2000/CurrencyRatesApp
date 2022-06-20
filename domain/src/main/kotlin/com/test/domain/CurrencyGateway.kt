package com.test.domain

import com.test.domain.entity.CurrencyNameModel
import com.test.domain.entity.RateModel
import javax.inject.Singleton

@Singleton
interface CurrencyGateway {
    suspend fun getAvailableCurrencies(): List<CurrencyNameModel>
    suspend fun getCurrencyRates(currencyNameModel: CurrencyNameModel): List<RateModel>
    fun getSelectedCurrency(): CurrencyNameModel?
    fun saveSelectedCurrency(selectedCurrency: CurrencyNameModel)
    fun getFavoriteCurrencyList(): List<String>
    fun saveFavoriteCurrencyRatesBase(favoriteList: List<String>)
}