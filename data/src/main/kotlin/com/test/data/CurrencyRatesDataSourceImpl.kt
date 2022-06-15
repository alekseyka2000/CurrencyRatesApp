package com.test.data

import android.content.Context
import android.content.pm.PackageManager
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyRatesDataSourceImpl @Inject constructor(
    applicationContext: Context
) : CurrencyRatesDataSource {

    companion object {
        private const val apiBaseUrl = "https://api.apilayer.com/exchangerates_data/"
        private const val apiKeyMetaDataName = "keyValue"
    }

    private val appInfo = applicationContext.packageManager
        .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)

    private val apiKey = appInfo.metaData[apiKeyMetaDataName].toString()

    private val currencyRatesApi by lazy {
        Retrofit.Builder()
            .baseUrl(apiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(CurrencyRatesApi::class.java)
    }

    override suspend fun getAvailableCurrencies(base: String) {
        currencyRatesApi.getAvailableCurrencies(apiKey)
    }

    override suspend fun getCurrencyRates(base: String) {
        currencyRatesApi.getCurrencyRates(apiKey, base)
    }
}