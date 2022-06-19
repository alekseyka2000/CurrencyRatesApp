package com.test.data

import com.test.data.api.CurrencyApiDataSource
import com.test.data.api.mapper.CurrencyRatesDtoToCurrencyMapper
import com.test.data.db.CurrenciesDao
import com.test.data.db.entity.Currency
import com.test.domain.CurrencyGateway
import com.test.domain.entity.CurrencyNameModel
import com.test.domain.entity.RateModel
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

    private var currentData: String? = null
    override suspend fun getAvailableCurrencies(): List<CurrencyNameModel> {
        val daoResult = currencyDao.getCurrencies()
        return if (daoResult.isEmpty()) {
            currencyDataSource.getAvailableCurrencies().symbols.names.map {
                currencyDao.insert(Currency(it.base, it.name))
                CurrencyNameModel(it.base, it.name)
            }
        } else {
            daoResult.map { CurrencyNameModel(it.base, it.name) }
        }
    }

    override suspend fun getCurrencyRates(currencyNameModel: CurrencyNameModel): List<RateModel> {
        val daoResult = currencyDao.getCurrencyRates(currencyNameModel.base)
        return if (daoResult.date == currentData) {
            daoResult.rates.map { RateModel(it.base, it.rate) }
        } else {
            val apiResult = currencyDataSource.getCurrencyRates(currencyNameModel.base)
            currencyDao.insert(
                CurrencyRatesDtoToCurrencyMapper().invoke(apiResult, currencyNameModel)
            )
            currentData = apiResult.date
            apiResult.rates.rates.map { RateModel(it.base, it.rate) }
        }
    }
}