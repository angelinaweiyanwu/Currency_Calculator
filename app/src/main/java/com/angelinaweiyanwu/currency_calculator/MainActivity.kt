package com.angelinaweiyanwu.currency_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.EditText
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // Umrechnungskurse
    private val rates = mapOf(
        "EUR-GBP" to 0.84,
        "EUR-CNY" to 7.90,
        "GBP-EUR" to 1.19,
        "GBP-CNY" to 9.38,
        "CNY-EUR" to 0.13,
        "CNY-GBP" to 0.11
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinner: Spinner = findViewById(R.id.spinner1)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.currency,
            R.layout.spinner_layout
        )
        adapter.setDropDownViewResource(R.layout.spinner_layout)
        spinner.adapter = adapter

        val spinner2: Spinner = findViewById(R.id.spinner2)
        val adapter2 = ArrayAdapter.createFromResource(
            this,
            R.array.currency,
            R.layout.spinner_layout
        )
        adapter2.setDropDownViewResource(R.layout.spinner_layout)
        spinner2.adapter = adapter2

        // Neue Komponenten für die Währungsumrechnung
        val fromAmount = findViewById<EditText>(R.id.editTextNumberDecimal)
        val toAmount = findViewById<EditText>(R.id.editTextNumberDecimal2)

        // Funktion zur Währungsumrechnung
        fun convert() {
            val input = fromAmount.text.toString()
            if (input.isEmpty()) {
                toAmount.setText("")
                return
            }

            try {
                val amount = input.toDouble()
                val from = spinner.selectedItem.toString()
                val to = spinner2.selectedItem.toString()

                val result = if (from == to) {
                    amount  // Gleiche Währung = gleicher Betrag
                } else {
                    val rate = rates["$from-$to"] ?: 1.0
                    amount * rate
                }

                toAmount.setText(String.format("%.2f", result))
            } catch (e: Exception) {
                toAmount.setText("")
            }
        }

        fromAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) { convert() }
        })

        val spinnerListener = object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                convert()
            }
            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {}
        }

        spinner.onItemSelectedListener = spinnerListener
        spinner2.onItemSelectedListener = spinnerListener
    }
}