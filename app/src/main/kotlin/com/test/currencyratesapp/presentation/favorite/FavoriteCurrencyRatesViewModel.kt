package com.test.currencyratesapp.presentation.favorite

import androidx.lifecycle.viewModelScope
import com.test.currencyratesapp.presentation.BaseCurrencyRatesViewModel
import com.test.currencyratesapp.presentation.PresentationRateModel
import com.test.domain.CurrencyGateway
import com.test.domain.GetCurrenciesUseCase
import com.test.domain.GetFavoriteCurrencyRatesUseCase
import com.test.domain.entity.CurrencyNameModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteCurrencyRatesViewModel @Inject constructor(
    getCurrenciesUseCase: GetCurrenciesUseCase,
    currencyGateway: CurrencyGateway,
    private val getFavoriteCurrencyRatesUseCase: GetFavoriteCurrencyRatesUseCase
) : BaseCurrencyRatesViewModel(getCurrenciesUseCase, currencyGateway) {

    init {
        getCurrencyNames()
    }

    override fun getNewRateList(model: CurrencyNameModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                currencyRatesMutableStateFlow.value =
                    getFavoriteCurrencyRatesUseCase.getFavoriteCurrencyRates(model)
                        .map {
                            PresentationRateModel(
                                it.base,
                                it.rate.toString(),
                                favoriteRatesBaseList.contains(it.base.uppercase())
                            )
                        }
            } catch (e: Exception) {
                handleException(e)
            }
        }
    }

}