package com.test.currencyratesapp

import androidx.lifecycle.ViewModel
import com.test.domain.GetCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularCurrencyRatesViewModel @Inject constructor(
    getCurrenciesUseCase: GetCurrenciesUseCase
) : ViewModel() {

    init {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
//            getCurrenciesUseCase.getCurrenciesList()
        }
    }
}