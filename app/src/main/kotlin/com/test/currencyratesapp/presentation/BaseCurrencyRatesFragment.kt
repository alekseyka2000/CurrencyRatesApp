package com.test.currencyratesapp.presentation

import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.currencyratesapp.R
import kotlinx.coroutines.flow.collect

/**
 * Fragment allow to get currency list
 *
 *  @author YarakhovichAA
 */

abstract class BaseCurrencyRatesFragment : Fragment() {

    protected abstract val viewModel: BaseCurrencyRatesViewModel

    private val currencyRateListAdapter = CurrencyRateListAdapter()

    protected fun setSpinnerContent(currenciesSpinner: Spinner) {
        lifecycleScope.launchWhenResumed {
            viewModel.currencyNameListStateFlow.collect { currencyNameList ->
                val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    currencyNameList
                )
                currenciesSpinner.adapter = adapter
            }
        }
    }

    protected fun setSpinnerListener(currenciesSpinner: Spinner) {
        currenciesSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(p: AdapterView<*>?, v: View?, position: Int, id: Long) {
                viewModel.wasCurrencySelected(position)
            }
        }
    }

    protected fun setFilterImageClickListener(filterIcon: ImageView) {
        filterIcon.setOnClickListener {
            (parentFragment?.parentFragment as NavHostFragment).setNotCheckableBottomNavigationView()
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_favoriteCurrencyRatesFragment_to_currencyRatesFilterFragment)
        }
    }

    protected fun setRecyclerView(recyclerView: RecyclerView) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = currencyRateListAdapter
        }
        lifecycleScope.launchWhenResumed {
            viewModel.currencyRatesStateFlow.collect { currencyRateList ->
                currencyRateListAdapter.setList(currencyRateList)
            }
        }
    }
}