package com.test.data.preference

interface PreferencesProvider {

    fun getSelectedCurrency(): String?
    fun saveSelectedCurrency(selectedCurrencyName: String)
    fun getFavoriteCurrencyRatesBase(): List<String>
    fun saveFavoriteCurrencyRatesBase(favoriteList: List<String>)
}