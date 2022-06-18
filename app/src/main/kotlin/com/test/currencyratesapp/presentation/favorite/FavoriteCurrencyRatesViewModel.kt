package com.test.currencyratesapp.presentation.favorite

import com.test.currencyratesapp.presentation.BaseCurrencyRatesViewModel
import com.test.domain.GetCurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteCurrencyRatesViewModel @Inject constructor(
    getCurrenciesUseCase: GetCurrenciesUseCase
) : BaseCurrencyRatesViewModel(getCurrenciesUseCase) {

    init {
        getCurrencyNames()
    }

    override fun wasCurrencySelected(position: Int) {
        currencyNameList[position]
        currencyNameList[position].name

    }
}