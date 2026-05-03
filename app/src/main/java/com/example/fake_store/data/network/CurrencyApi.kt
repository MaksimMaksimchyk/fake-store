package com.example.fake_store.data.network

import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApi {
    @GET("rates/{id}")
    suspend fun getCurrencyRate(@Path("id") currencyId: Int): CurrencyRateDTO
}
