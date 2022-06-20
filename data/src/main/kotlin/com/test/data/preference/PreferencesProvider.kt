package com.test.data.preference

import com.test.domain.entity.CurrencyNameModel

interface PreferencesProvider {

    fun getSelectedCurrency(): CurrencyNameModel?
    fun saveSelectedCurrency(selectedCurrency: CurrencyNameModel)
    fun getFavoriteCurrencyRatesBase(): List<String>
    fun saveFavoriteCurrencyRatesBase(favoriteList: List<String>)
    fun getFilterTypeName(): String?
    fun saveFilterTypeName(filterTypeName: String)
    fun isOrderFilterByAscending(): Boolean
    fun isOrderFilterByAscending(isByAscending: Boolean)
}