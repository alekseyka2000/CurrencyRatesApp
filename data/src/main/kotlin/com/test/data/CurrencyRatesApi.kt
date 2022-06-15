package com.test.data

import com.test.data.entity.AvailableCurrencyDto
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyRatesApi {

    @GET("latest")
    suspend fun getAvailableCurrencies(@Query("apikey") apiKey: String): AvailableCurrencyDto

    @GET("latest")
    suspend fun getCurrencyRates(
        @Query("apikey") apiKey: String,
        @Query("base") base: String
    ): AvailableCurrencyDto
}