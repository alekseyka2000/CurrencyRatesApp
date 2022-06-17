package com.test.data

import com.test.data.api.CurrencyApiDataSource
import com.test.data.db.CurrenciesDao
import com.test.data.db.entity.Currency
import com.test.data.db.entity.Rate
import com.test.domain.CurrencyGateway
import com.test.domain.entity.CurrencyModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CurrencyGatewayImpl @Inject constructor(
    private val currencyDataSource: CurrencyApiDataSource,
    private val currencyDao: CurrenciesDao
) : CurrencyGateway {
    override suspend fun getAvailableCurrencies(): List<CurrencyModel> {
        val apiResult = currencyDataSource.getAvailableCurrencies()
        return if (apiResult.success){
            apiResult.symbols.names.map {
                currencyDao.insert(Currency(it.base, it.name))
                CurrencyModel(it.base, it.name)
            }
        } else {
            currencyDao.getCurrencies().map { CurrencyModel(it.base, it.name) }
        }
    }

    override suspend fun getCurrencyRates(base: String) {
        TODO("Not yet implemented")
    }
}