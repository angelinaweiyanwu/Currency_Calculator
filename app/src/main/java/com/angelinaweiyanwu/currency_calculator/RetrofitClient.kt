package com.angelinaweiyanwu.currency_calculator

object RetrofitClient {
    private const val BASE_URL = "https://v6.exchangerate-api.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: ExchangeRateApi = retrofit.create(ExchangeRateApi::class.java)
}