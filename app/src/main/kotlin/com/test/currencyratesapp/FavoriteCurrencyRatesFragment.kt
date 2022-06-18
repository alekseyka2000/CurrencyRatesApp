package com.test.currencyratesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

/**
 * Fragment for show favorite rates for selected currency
 *
 *  @author YarakhovichAA
 */

class FavoriteCurrencyRatesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_currency_rates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View){
        val filterIcon = view.findViewById<ImageView>(R.id.filterIcon)
        val currenciesSpinner = view.findViewById<Spinner>(R.id.currenciesSpinner)
        val rateList = view.findViewById<RecyclerView>(R.id.rateList)

        filterIcon.setOnClickListener{
            (parentFragment?.parentFragment as NavHostFragment).setNotCheckableBottomNavigationView()
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_favoriteCurrencyRatesFragment_to_currencyRatesFilterFragment)
        }
    }
}