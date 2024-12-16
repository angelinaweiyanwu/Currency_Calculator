package com.angelinaweiyanwu.currency_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
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
        val spinner: Spinner = findViewById(R.id.spinner)
        //Adapter mit Array aus den Ressourcen

        val adapter = adapter.createFromResource(
            this,
            R.array.currency,
            R.layout.spinner_layout            )
        adapter.setDropDownViewResource(R.layout.spinner_layout)
        spinner.adapter = adapter
        val spinner2 : Spinner = findViewById(R.id.spinner2)
        val adapter2 = adapter.createFromResource(
            this,            R.array.currency,            R.layout.spinner_layout        )
        adapter.setDropDownViewResource(R.layout.spinner_layout)
        spinner2.adapter = adapter
    }
}