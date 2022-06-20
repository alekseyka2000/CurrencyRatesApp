package com.test.currencyratesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.domain.CurrencyGateway
import com.test.domain.GetCurrenciesUseCase
import com.test.domain.entity.CurrencyNameModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * View model allow to base functions for currency rates view model
 *
 *  @author YarakhovichAA
 */

abstract class BaseCurrencyRatesViewModel(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val currencyGateway: CurrencyGateway
) : ViewModel() {

    var selectedCurrency = currencyGateway.getSelectedCurrency()
        protected set

    protected var favoriteRatesBaseList =
        currencyGateway.getFavoriteCurrencyList().toMutableList()

    protected val currencyRatesMutableStateFlow =
        MutableStateFlow<List<PresentationRateModel>>(emptyList())
    val currencyRatesStateFlow: StateFlow<List<PresentationRateModel>> =
        currencyRatesMutableStateFlow

    private val currencyNameListMutableStateFlow = MutableStateFlow<List<String>>(emptyList())
    val currencyNameListStateFlow: StateFlow<List<String>> = currencyNameListMutableStateFlow

    private val errorMutableStateFlow = MutableStateFlow<String?>(null)
    val errorStateFlow: StateFlow<String?> = errorMutableStateFlow

    private lateinit var currencyNameList: List<CurrencyNameModel>

    abstract fun getNewRateList(model: CurrencyNameModel)

    fun wasCurrencySelected(position: Int) {
        saveNewSelectedCurrency(position)
        getNewRateList(currencyNameList[position])
    }

    fun wasScreenStarted() {
        currencyGateway.getSelectedCurrency()?.let { getNewRateList(it) }
    }

    fun wasFavoriteStatusChanged(clickedItemBase: String) {
        if (favoriteRatesBaseList.contains(clickedItemBase)) {
            favoriteRatesBaseList.remove(clickedItemBase)
        } else {
            favoriteRatesBaseList.add(clickedItemBase)
        }
        currencyGateway.saveFavoriteCurrencyRatesBase(favoriteRatesBaseList)
    }

    protected fun getCurrencyNames() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                currencyNameList = getCurrenciesUseCase.getCurrenciesList()
                currencyNameListMutableStateFlow.value = currencyNameList.map { it.name }
            } catch (e: Exception) {
                handleException(e)
            }
        }
    }

    protected fun handleException(exception: Exception) {
        errorMutableStateFlow.value = exception.message
    }

    private fun saveNewSelectedCurrency(position: Int) {
        val newSelectedItem = currencyNameList[position]
        currencyGateway.saveSelectedCurrency(currencyNameList[position])
        selectedCurrency = newSelectedItem
    }
}