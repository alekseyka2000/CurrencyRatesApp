package com.test.currencyratesapp.presentation.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.test.currencyratesapp.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment for setting currency rate list filters
 *
 *  @author YarakhovichAA
 */

@AndroidEntryPoint
class CurrencyRatesFilterFragment : Fragment() {

    private val viewModel: CurrencyRatesFilterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currency_rates_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RadioGroup>(R.id.filterTypeRadioGroup).apply {
            check(viewModel.savedFilterTypeId)
            setOnCheckedChangeListener { _, checkedId ->
                viewModel.wasFilterTypeSelected(checkedId)
            }
        }
        view.findViewById<RadioGroup>(R.id.filterOrderRadioGroup).apply {
            check(viewModel.savedFilterOrderId)
            setOnCheckedChangeListener { _, checkedId ->
                viewModel.wasOrderFilterSelected(checkedId)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        resetOnBackPressed()
    }

    private fun resetOnBackPressed() {
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        val backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navController.popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, backPressedCallback)
    }
}