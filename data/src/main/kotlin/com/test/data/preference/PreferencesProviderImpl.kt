package com.test.data.preference

import android.content.SharedPreferences
import com.test.domain.entity.CurrencyNameModel

class PreferencesProviderImpl(private val preferences: SharedPreferences) : PreferencesProvider {

    companion object {
        private const val SELECTED_CURRENCY_KEY = "selectedCurrencyKey"
        private const val FAVORITE_CURRENCY_RATES_KEY = "favoriteCurrencyRates"
        private const val FILTER_TYPE_NAME_KEY = "filterTypeName"
        private const val FILTER_ORDER_KEY = "filterOrder"
    }

    private val preferencesEditor = preferences.edit()

    override fun getSelectedCurrency(): CurrencyNameModel? {
        val selectedCurrencyString = preferences.getString(SELECTED_CURRENCY_KEY, null)
        return if (selectedCurrencyString != null && selectedCurrencyString.contains(",")) {
            val currencyField = selectedCurrencyString.split(",")
            CurrencyNameModel(currencyField.first(), currencyField.last())
        } else null
    }

    override fun saveSelectedCurrency(selectedCurrency: CurrencyNameModel) {
        val selectedCurrencyString = "${selectedCurrency.base},${selectedCurrency.name}"
        preferencesEditor.putString(SELECTED_CURRENCY_KEY, selectedCurrencyString).commit()
    }

    override fun getFavoriteCurrencyRatesBase(): List<String> {
        val favoriteString = preferences.getString(FAVORITE_CURRENCY_RATES_KEY, null)
        return if (favoriteString.isNullOrEmpty()) {
            emptyList()
        } else {
            favoriteString.split(",")
        }
    }

    override fun saveFavoriteCurrencyRatesBase(favoriteList: List<String>) {
        val favoriteString = favoriteList.joinToString(",")
        preferencesEditor.putString(FAVORITE_CURRENCY_RATES_KEY, favoriteString).commit()
    }

    override fun getFilterTypeName() = preferences.getString(FILTER_TYPE_NAME_KEY, null)

    override fun saveFilterTypeName(filterTypeName: String) {
        preferencesEditor.putString(FILTER_TYPE_NAME_KEY, filterTypeName).commit()
    }

    override fun isOrderFilterByAscending() = preferences.getBoolean(FILTER_ORDER_KEY, true)

    override fun isOrderFilterByAscending(isByAscending: Boolean) {
        preferencesEditor.putBoolean(FILTER_ORDER_KEY, isByAscending).commit()
    }
}