package com.test.currencyratesapp.presentation

import androidx.lifecycle.ViewModel
import com.test.data.preference.PreferencesProvider
import com.test.domain.GetCurrenciesUseCase
import com.test.domain.entity.CurrencyNameModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseCurrencyRatesViewModel(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val preferencesProvider: PreferencesProvider
) : ViewModel() {

    var selectedCurrency = preferencesProvider.getSelectedCurrency()
        protected set

    protected var favoriteRatesBaseList =
        preferencesProvider.getFavoriteCurrencyRatesBase().toMutableList()

    protected lateinit var currencyNameList: List<CurrencyNameModel>

    protected val currencyRatesMutableStateFlow =
        MutableStateFlow<List<PresentationRateModel>>(emptyList())
    val currencyRatesStateFlow: StateFlow<List<PresentationRateModel>> =
        currencyRatesMutableStateFlow

    private val currencyNameListMutableStateFlow = MutableStateFlow<List<String>>(emptyList())
    val currencyNameListStateFlow: StateFlow<List<String>> = currencyNameListMutableStateFlow

    abstract fun getRateForNewSelectedCurrency(position: Int)

    fun wasCurrencySelected(position: Int) {
        saveNewSelectedCurrency(position)
        getRateForNewSelectedCurrency(position)
    }

    fun wasFavoriteStatusChanged(clickedItemBase: String) {
        if (favoriteRatesBaseList.contains(clickedItemBase)) {
            favoriteRatesBaseList.remove(clickedItemBase)
        } else {
            favoriteRatesBaseList.add(clickedItemBase)
        }
        preferencesProvider.saveFavoriteCurrencyRatesBase(favoriteRatesBaseList)
    }

    protected fun getCurrencyNames() {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            currencyNameList = getCurrenciesUseCase.getCurrenciesList()
            currencyNameListMutableStateFlow.value = currencyNameList.map { it.name }
        }
    }

    private fun saveNewSelectedCurrency(position: Int) {
        val newSelectedItem = currencyNameList[position].name
        preferencesProvider.saveSelectedCurrency(currencyNameList[position].name)
        selectedCurrency = newSelectedItem
    }
}