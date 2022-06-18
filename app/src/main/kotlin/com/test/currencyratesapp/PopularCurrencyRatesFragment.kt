package com.test.currencyratesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.scopes.FragmentScoped

/**
 * Fragment for show popular rates for selected currency
 *
 *  @author YarakhovichAA
 */

@FragmentScoped
class PopularCurrencyRatesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular_currency_rates, container, false)
    }
}