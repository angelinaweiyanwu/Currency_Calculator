package com.angelinaweiyanwu.currency_calculator

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeRateApi {
    @GET("v6/{16d68c576083eb547208dbe0}/latest/{currency}")
    suspend fun getLatestRates(
        @Path("16d68c576083eb547208dbe0") apiKey: String,
        @Path("currency") baseCurrency: String
    ): Response<ExchangeRateResponse>
    }

