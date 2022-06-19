package com.test.currencyratesapp.presentation.popular

import com.test.currencyratesapp.presentation.BaseCurrencyRatesViewModel
import com.test.currencyratesapp.presentation.PresentationRateModel
import com.test.data.preference.PreferencesProvider
import com.test.domain.GetCurrenciesUseCase
import com.test.domain.GetPopularCurrencyRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularCurrencyRatesViewModel @Inject constructor(
    getCurrenciesUseCase: GetCurrenciesUseCase,
    private val getPopularCurrencyRatesUseCase: GetPopularCurrencyRatesUseCase,
    private val preferencesProvider: PreferencesProvider
) : BaseCurrencyRatesViewModel(getCurrenciesUseCase, preferencesProvider) {

    init {
        getCurrencyNames()
    }

    override fun getRateForNewSelectedCurrency(position: Int) {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            currencyRatesMutableStateFlow.value =
                getPopularCurrencyRatesUseCase.getPopularCurrencyRates(currencyNameList[position])
                    .map {
                        PresentationRateModel(
                            it.base,
                            it.rate.toString(),
                            favoriteRatesBaseList.contains(it.base.uppercase())
                        )
                    }
        }
    }
}