package com.test.data.api

import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.test.data.api.deserializer.RatesDeserializer
import com.test.data.api.deserializer.SymbolsDeserializer
import com.test.data.api.entity.Rates
import com.test.data.api.entity.Symbols

import retrofit2.Converter
import javax.inject.Singleton

@Singleton
class CurrencyApiService {

    companion object {
        private const val apiBaseUrl = "https://api.apilayer.com/exchangerates_data/"
    }

    fun getCurrencyRatesApi() : CurrencyApi {
        return Retrofit.Builder()
            .baseUrl(apiBaseUrl)
            .addConverterFactory(createGsonConverter())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(CurrencyApi::class.java)
    }

    private fun createGsonConverter(): Converter.Factory {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(Symbols::class.java, SymbolsDeserializer())
        gsonBuilder.registerTypeAdapter(Rates::class.java, RatesDeserializer())
        val gson = gsonBuilder.create()
        return GsonConverterFactory.create(gson)
    }
}