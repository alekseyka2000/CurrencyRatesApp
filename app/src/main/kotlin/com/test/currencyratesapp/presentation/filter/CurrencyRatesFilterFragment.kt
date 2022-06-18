package com.test.currencyratesapp.presentation.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.currencyratesapp.R

/**
 * A simple [Fragment] subclass.
 */
class CurrencyRatesFilterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currency_rates_filter, container, false)
    }
}