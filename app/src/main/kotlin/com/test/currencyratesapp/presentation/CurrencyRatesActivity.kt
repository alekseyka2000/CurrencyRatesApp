package com.test.currencyratesapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.currencyratesapp.R
import dagger.hilt.android.AndroidEntryPoint

/**
 *  @author YarakhovichAA
 */

@AndroidEntryPoint
class CurrencyRatesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}