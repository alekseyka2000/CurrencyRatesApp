package com.test.currencyratesapp.presentation.filter

import androidx.lifecycle.ViewModel
import com.test.currencyratesapp.R
import com.test.domain.enum.Filters
import com.test.data.preference.PreferencesProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *  @author YarakhovichAA
 */

@HiltViewModel
class CurrencyRatesFilterViewModel @Inject constructor(
    private val preferencesProvider: PreferencesProvider
) : ViewModel() {

    var savedFilterTypeId = when (preferencesProvider.getFilterTypeName()) {
        Filters.ALPHABETICALLY.name -> R.id.alphabeticallyRadioButton
        Filters.BY_VALUE.name -> R.id.byValueRadioButton
        else -> R.id.doNotApplyRadioButton
    }
        private set

    var savedFilterOrderId = if (preferencesProvider.isOrderFilterByAscending()) {
        R.id.ascendingRadioButton
    } else {
        R.id.descendingRadioButton
    }
        private set

    fun wasFilterTypeSelected(checkedFilterId: Int) {
        val checkedFilterName = when (checkedFilterId) {
            R.id.alphabeticallyRadioButton -> Filters.ALPHABETICALLY.name
            R.id.byValueRadioButton -> Filters.BY_VALUE.name
            else -> Filters.DO_NOT_APPLY.name
        }
        preferencesProvider.saveFilterTypeName(checkedFilterName)
    }

    fun wasOrderFilterSelected(selectedOrderFilter: Int) {
        preferencesProvider.isOrderFilterByAscending(selectedOrderFilter == R.id.ascendingRadioButton)
    }
}