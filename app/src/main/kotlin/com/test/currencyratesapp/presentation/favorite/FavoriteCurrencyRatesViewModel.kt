package com.test.currencyratesapp.presentation.favorite

import com.test.currencyratesapp.presentation.BaseCurrencyRatesViewModel
import com.test.currencyratesapp.presentation.PresentationRateModel
import com.test.data.preference.PreferencesProvider
import com.test.domain.GetCurrenciesUseCase
import com.test.domain.GetFavoriteCurrencyRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteCurrencyRatesViewModel @Inject constructor(
    getCurrenciesUseCase: GetCurrenciesUseCase,
    preferencesProvider: PreferencesProvider,
    private val getFavoriteCurrencyRatesUseCase: GetFavoriteCurrencyRatesUseCase
) : BaseCurrencyRatesViewModel(getCurrenciesUseCase, preferencesProvider) {

    init {
        getCurrencyNames()
    }

    override fun getRateForNewSelectedCurrency(position: Int) {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            currencyRatesMutableStateFlow.value =
                getFavoriteCurrencyRatesUseCase.getFavoriteCurrencyRates(currencyNameList[position])
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