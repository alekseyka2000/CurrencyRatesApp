package com.test.currencyratesapp.presentation.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.test.currencyratesapp.R
import com.test.currencyratesapp.presentation.BaseCurrencyRatesFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

/**
 * Fragment for show popular rates for selected currency
 *
 *  @author YarakhovichAA
 */

@AndroidEntryPoint
class PopularCurrencyRatesFragment : BaseCurrencyRatesFragment() {

    override val viewModel: PopularCurrencyRatesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular_currency_rates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        val filterIcon = view.findViewById<ImageView>(R.id.filterIcon)
        val currenciesSpinner = view.findViewById<Spinner>(R.id.currenciesSpinner)
        val rateList = view.findViewById<RecyclerView>(R.id.rateList)

        setFilterImageClickListener(filterIcon)
        setSpinnerListener(currenciesSpinner)
        setSpinnerContent(currenciesSpinner)
    }
}