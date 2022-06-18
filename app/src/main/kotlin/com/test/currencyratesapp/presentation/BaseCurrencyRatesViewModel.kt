package com.test.currencyratesapp.presentation

import androidx.lifecycle.ViewModel
import com.test.domain.GetCurrenciesUseCase
import com.test.domain.entity.CurrencyModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

abstract class BaseCurrencyRatesViewModel(
    private val getCurrenciesUseCase: GetCurrenciesUseCase
) : ViewModel() {

    protected lateinit var currencyList: List<CurrencyModel>
    private val currencyNameListMutableStateFlow = MutableStateFlow<List<String>>(emptyList())
    val currencyNameListStateFlow: StateFlow<List<String>> = currencyNameListMutableStateFlow

    abstract fun wasCurrencySelected(position: Int)

    protected fun getCurrencyNames() {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            currencyList = getCurrenciesUseCase.getCurrenciesList()
            currencyNameListMutableStateFlow.value = currencyList.map { it.name }
        }
    }
}