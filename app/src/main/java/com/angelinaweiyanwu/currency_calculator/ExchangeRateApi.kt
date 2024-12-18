package com.angelinaweiyanwu.currency_calculator

class ExchangeRateApi {
    interface ExchangeRateApi {
        @GET("v6/16d68c576083eb547208dbe0/latest/{currency}")
        suspend fun getLatestRates(@Path("currency") baseCurrency: String): Response<ExchangeRateResponse>
    }

}