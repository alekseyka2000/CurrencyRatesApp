package com.test.data

import com.test.data.api.CurrencyApiDataSource
import com.test.data.db.CurrenciesDao
import com.test.data.db.entity.Currency
import com.test.domain.CurrencyGateway
import com.test.domain.entity.CurrencyModel
import javax.inject.Inject

/**
 * Class provide currencies data
 *
 *  @author YarakhovichAA
 */

class CurrencyGatewayImpl @Inject constructor(
    private val currencyDataSource: CurrencyApiDataSource,
    private val currencyDao: CurrenciesDao
) : CurrencyGateway {
    override suspend fun getAvailableCurrencies(): List<CurrencyModel> {

        val daoResult = currencyDao.getCurrencies()
        return if (daoResult.isEmpty()) {
            currencyDataSource.getAvailableCurrencies().symbols.names.map {
                currencyDao.insert(Currency(it.base, it.name))
                CurrencyModel(it.base, it.name)
            }
        } else {
            daoResult.map { CurrencyModel(it.base, it.name) }
        }
    }

    override suspend fun getCurrencyRates(base: String) {
        TODO("Not yet implemented")
    }
}