package com.angelinaweiyanwu.currency_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CurrencyViewModel
    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var editTextFrom: EditText
    private lateinit var editTextTo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[CurrencyViewModel::class.java]

        spinner1 = findViewById(R.id.spinner1)
        spinner2 = findViewById(R.id.spinner2)
        editTextFrom = findViewById(R.id.editTextNumberDecimal)
        editTextTo = findViewById(R.id.editTextNumberDecimal2)

        setupSpinners()
        setupTextWatcher()
        observeRates()

        // Initial rate fetch
        viewModel.fetchRates("EUR")
    }

    private fun setupSpinners() {
        ArrayAdapter.createFromResource(
            this,
            R.array.currency,
            R.layout.spinner_layout
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_layout)
            spinner1.adapter = adapter
            spinner2.adapter = adapter
        }

        val spinnerListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateConversion()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        spinner1.onItemSelectedListener = spinnerListener
        spinner2.onItemSelectedListener = spinnerListener
    }

    private fun setupTextWatcher() {
        editTextFrom.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                updateConversion()
            }
        })
    }

    private fun observeRates() {
        viewModel.exchangeRates.observe(this) {
            updateConversion()
        }

        viewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateConversion() {
        val amount = editTextFrom.text.toString().toDoubleOrNull() ?: 0.0
        val fromCurrency = spinner1.selectedItem?.toString() ?: return
        val toCurrency = spinner2.selectedItem?.toString() ?: return

        val result = viewModel.convertCurrency(amount, fromCurrency, toCurrency)
        editTextTo.setText(String.format("%.2f", result))
    }
}