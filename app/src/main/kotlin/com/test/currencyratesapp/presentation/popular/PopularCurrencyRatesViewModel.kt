package com.test.currencyratesapp.presentation.popular

import com.test.currencyratesapp.presentation.BaseCurrencyRatesViewModel
import com.test.domain.GetCurrenciesUseCase
import com.test.domain.GetPopularCurrencyRatesUseCase
import com.test.domain.entity.RateModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularCurrencyRatesViewModel @Inject constructor(
    getCurrenciesUseCase: GetCurrenciesUseCase,
    private val getPopularCurrencyRatesUseCase: GetPopularCurrencyRatesUseCase
) : BaseCurrencyRatesViewModel(getCurrenciesUseCase) {

    init {
        getCurrencyNames()
    }

    override fun wasCurrencySelected(position: Int) {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            currencyRatesMutableStateFlow.value =
                getPopularCurrencyRatesUseCase.getPopularCurrencyRates(currencyNameList[position])
        }
    }
}