package com.angelinaweiyanwu.currency_calculator

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeRateApi {
    @GET("v6/{apiKey}/latest/{currency}")
    suspend fun getLatestRates(
        @Path("apiKey") apiKey: String,
        @Path("currency") baseCurrency: String
    ): Response<ExchangeRateResponse>
}
