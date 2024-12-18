package com.angelinaweiyanwu.currency_calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CurrencyViewModel : ViewModel() {
    private val _exchangeRates = MutableLiveData<Map<String, Double>>()
    val exchangeRates: LiveData<Map<String, Double>> = _exchangeRates

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchRates(baseCurrency: String) {
        viewModelScope.launch {
            try {
                // Hier holen wir den API Key aus den Ressourcen
                val response = RetrofitClient.api.getLatestRates(
                    apiKey = "16d68c576083eb547208dbe0",
                    baseCurrency = baseCurrency
                )
                if (response.isSuccessful && response.body()?.result == "success") {
                    response.body()?.let { rateResponse ->
                        _exchangeRates.value = ExchangeRateResponse.conversion_rates
                    }
                } else {
                    _error.value = "Failed to fetch rates"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "An error occurred"
            }
        }
    }

    fun convertCurrency(amount: Double, fromCurrency: String, toCurrency: String): Double {
        val rates = _exchangeRates.value ?: return 0.0
        val fromRate = rates[fromCurrency] ?: return 0.0
        val toRate = rates[toCurrency] ?: return 0.0
        return amount * (toRate / fromRate)
    }
}