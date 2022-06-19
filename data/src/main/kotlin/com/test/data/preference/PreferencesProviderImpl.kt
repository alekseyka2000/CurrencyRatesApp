package com.test.data.preference

import android.content.SharedPreferences

class PreferencesProviderImpl(private val preferences: SharedPreferences) : PreferencesProvider {

    companion object {
        private const val SELECTED_CURRENCY_KEY = "selectedCurrencyKey"
        private const val FAVORITE_CURRENCY_RATES_KEY = "favoriteCurrencyRates"
    }

    private val preferencesEditor = preferences.edit()

    override fun getSelectedCurrency() = preferences.getString(SELECTED_CURRENCY_KEY, null)

    override fun saveSelectedCurrency(selectedCurrencyName: String) {
        preferencesEditor.putString(SELECTED_CURRENCY_KEY, selectedCurrencyName).commit()
    }

    override fun getFavoriteCurrencyRatesBase(): List<String> {
        val favoriteString = preferences.getString(FAVORITE_CURRENCY_RATES_KEY, null)
        return if (favoriteString.isNullOrEmpty()){
            emptyList()
        } else {
            favoriteString.split(",")
        }
    }

    override fun saveFavoriteCurrencyRatesBase(favoriteList: List<String>) {
        val favoriteString = favoriteList.joinToString(",")
        preferencesEditor.putString(FAVORITE_CURRENCY_RATES_KEY, favoriteString).commit()
    }
}