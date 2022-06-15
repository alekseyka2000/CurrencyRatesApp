package com.test.data.api

import com.test.data.api.entity.AvailableCurrencyDto
import com.test.data.api.entity.CurrencyRatesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("latest")
    suspend fun getAvailableCurrencies(@Query("apikey") apiKey: String): AvailableCurrencyDto

    @GET("latest")
    suspend fun getCurrencyRates(
        @Query("apikey") apiKey: String,
        @Query("base") base: String
    ): CurrencyRatesDto
}