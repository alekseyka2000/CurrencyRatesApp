package com.test.data.api

import android.content.Context
import android.content.pm.PackageManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyApiDataSourceImpl @Inject constructor(
    applicationContext: Context,
    currencyApiService: CurrencyApiService
) : CurrencyApiDataSource {

    companion object {
        private const val apiKeyMetaDataName = "keyValue"
    }

    private val appInfo = applicationContext.packageManager
        .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)

    private val apiKey = appInfo.metaData[apiKeyMetaDataName].toString()

    private val currencyApi = currencyApiService.getCurrencyRatesApi()

    override suspend fun getAvailableCurrencies() = currencyApi.getAvailableCurrencies(apiKey)

    override suspend fun getCurrencyRates(base: String) {
        currencyApi.getCurrencyRates(apiKey, base)
    }
}