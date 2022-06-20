package com.test.currencyratesapp.presentation.popular

import com.test.currencyratesapp.presentation.BaseCurrencyRatesViewModel
import com.test.currencyratesapp.presentation.PresentationRateModel
import com.test.domain.CurrencyGateway
import com.test.domain.GetCurrenciesUseCase
import com.test.domain.GetPopularCurrencyRatesUseCase
import com.test.domain.entity.CurrencyNameModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularCurrencyRatesViewModel @Inject constructor(
    getCurrenciesUseCase: GetCurrenciesUseCase,
    currencyGateway: CurrencyGateway,
    private val getPopularCurrencyRatesUseCase: GetPopularCurrencyRatesUseCase
) : BaseCurrencyRatesViewModel(getCurrenciesUseCase, currencyGateway) {

    init {
        getCurrencyNames()
    }

    override fun getNewRateList(model: CurrencyNameModel) {
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        scope.launch {
            currencyRatesMutableStateFlow.value =
                getPopularCurrencyRatesUseCase.getPopularCurrencyRates(model)
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